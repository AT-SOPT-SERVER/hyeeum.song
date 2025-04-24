package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostRepository;
import org.sopt.validator.TitleValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.sopt.constant.PostConstant.POST_TIME_LIMIT;
import static org.sopt.constant.PostConstant.TITLE_LENGTH_LIMIT;
import static org.sopt.exception.Error.*;
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

    public Optional<Post> findPostById(final long id) {
        return postRepository.findById(id);
    }

    public boolean deletePostById(final long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updatePostTitle(final long updateId, String newTitle) {
        validateTitle(newTitle);

        Optional<Post> post = postRepository.findById(updateId);
        if (post.isEmpty()) return false;

        post.get().updateTitle(newTitle);
        return true;
    }

        public void validateTimeStamp() {
        LocalDateTime lastTimeStamp = getLastTimeStamp();

        if (lastTimeStamp == null) return;
        validateLastTimeStampLimit(getLastTimeStamp(), POST_TIME_LIMIT);
    }

    public void validateTitle(final String title) {
        if (TitleValidator.isTitleBlank(title)) throw new CustomException(TITLE_BLANK_ERROR);
        if (TitleValidator.isTitleExceedsLength(title, TITLE_LENGTH_LIMIT))
            throw new CustomException(TITLE_LENGTH_ERROR);
        // TO.파트장님 !!!!!!!!!!!!!!!!!!!!!!!
        // 요기 아래처럼 해도 괜찮나요? Validator 를 사용해서 구현하고 싶었는데 아무래도 list 전체에 접근해야해서 Repository 에서 메소드를 불러왔어요
        if (postRepository.isTitleDuplicated(title))
            throw new CustomException(TITLE_DUPLICATED_ERROR);
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }
}
