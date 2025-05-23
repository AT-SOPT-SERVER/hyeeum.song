package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.exception.PostNotFoundException;
import org.sopt.exception.TitleBlankException;
import org.sopt.exception.TitleDuplicatedException;
import org.sopt.exception.TitleLengthException;
import org.sopt.repository.PostRepository;
import org.sopt.validator.TitleValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.sopt.constant.LimitConstant.POST_TIME_LIMIT;
import static org.sopt.constant.LimitConstant.TITLE_LENGTH_LIMIT;
import static org.sopt.util.LastTimeStampGeneratorUtil.getLastTimeStamp;
import static org.sopt.util.LastTimeStampGeneratorUtil.setLastTimeStamp;
import static org.sopt.validator.TimeStampValidator.validateLastTimeStampLimit;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(final String title) {
        validateTimeStamp();
        validateTitle(title);

        Post post = new Post(title);
        setLastTimeStamp();

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(final long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public void deletePostById(final long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
        throw new PostNotFoundException(); // TODO: 이걸 여기서 던,,지는게 맞겠지
    }

    @Transactional
    public void updatePostTitle(final long updateId, String newTitle) {
        validateTitle(newTitle);

        Optional<Post> post = postRepository.findById(updateId);
        if (post.isEmpty()) throw new PostNotFoundException();

        post.get().updateTitle(newTitle);
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

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }
}
