package org.sopt.service;

import org.sopt.constant.DataBaseConstant;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.exception.*;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.sopt.validator.TitleValidator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.sopt.constant.LimitConstant.POST_TIME_LIMIT;
import static org.sopt.constant.LimitConstant.TITLE_LENGTH_LIMIT;
import static org.sopt.util.LastTimeStampGeneratorUtil.getLastTimeStamp;
import static org.sopt.util.LastTimeStampGeneratorUtil.setLastTimeStamp;
import static org.sopt.validator.TimeStampValidator.validateLastTimeStampLimit;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createPost(final Long userId, final String title, final String content) {
        validateTimeStamp();
        validateTitle(title);
        validateContent(content);

        User user = findUserById(userId);
        Post post = new Post(title, content, user);

        setLastTimeStamp();
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, DataBaseConstant.ID));
    }

    public Post findPostById(final long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public void deletePostById(final long userId, final long id) {
        Post post = findPostById(id);
        boolean isPostOwner = post.getUser().getId() == userId;

        if (!isPostOwner) throw new NotPostUserErrorException();

        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePostTitle(final long userId, final long updateId, String newTitle) {
        Post post = findPostById(updateId);
        boolean isPostOwner = post.getUser().getId() == userId;

        if (!isPostOwner) throw new NotPostUserErrorException();

        post.updateTitle(newTitle);
    }

    // TODO: 이 기능이 여기있어도 되는,,,? 책임분리가 명확해보이지 않음ㅠ -> 인터셉터로 하는 방법 찾아보기
    public User findUserById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public void validateTimeStamp() {
        LocalDateTime lastTimeStamp = getLastTimeStamp();

        if (lastTimeStamp == null) return;
        validateLastTimeStampLimit(getLastTimeStamp(), POST_TIME_LIMIT);
    }

    public void validateTitle(final String title) {
        if (TitleValidator.isTitleBlank(title)) throw new TitleBlankException();
        if (TitleValidator.isTitleExceedsLength(title, TITLE_LENGTH_LIMIT))
            throw new TitleLengthException(TITLE_LENGTH_LIMIT);
        // TO.파트장님 !!!!!!!!!!!!!!!!!!!!!!!
        // 요기 아래처럼 해도 괜찮나요? Validator 를 사용해서 구현하고 싶었는데 아무래도 list 전체에 접근해야해서 Repository 에서 메소드를 불러왔어요
        if (postRepository.isTitleDuplicated(title))
            throw new TitleDuplicatedException();
    }

    public void validateContent(final String content) {
        // TODO: title content 모두 공백 처리 방식이 같으므로 통일하기
        if (content == null || content.isBlank()) throw new ContentBlankException();
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }
}
