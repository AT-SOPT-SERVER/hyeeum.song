package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.validator.TitleValidator;

import java.time.LocalDateTime;
import java.util.List;

import static org.sopt.util.IdentifierGeneratorUtil.generateIdentifier;
import static org.sopt.util.LastTimeStampGeneratorUtil.getLastTimeStamp;
import static org.sopt.util.LastTimeStampGeneratorUtil.setLastTimeStamp;
import static org.sopt.validator.TimeStampValidator.validateLastTimeStampLimit;

//레포지토리를 가져와서 사용하는 역할 (저장소 가져오기) - 로직
public class PostService {
    private PostRepository postRepository = new PostRepository();
    private static final int TITLE_LENGTH_LIMIT = 30;
    private static final int POST_TIME_LIMIT = 3;

    public void createPost(String title) {
        validateTimeStamp();
        validateTitle(title);

        Post post = new Post(generateIdentifier(), title);
        setLastTimeStamp();

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.deletePostById(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        validateTitle(newTitle);

        Post post = postRepository.findPostById(updateId);
        if (post == null) return false;

        post.setTitle(newTitle);
        return true;
    }

    public void validateTimeStamp() {
        LocalDateTime lastTimeStamp = getLastTimeStamp();

        if (lastTimeStamp == null) return;
        validateLastTimeStampLimit(getLastTimeStamp(), POST_TIME_LIMIT);
    }

    public void validateTitle(String title) {
        if (TitleValidator.isTitleBlank(title)) throw new IllegalArgumentException("제목은 한 글자 이상 입력해야합니다.");
        if (TitleValidator.isTitleExceedsLength(title, TITLE_LENGTH_LIMIT))
            throw new IllegalArgumentException("제목은 30글자 이하만 입력 가능합니다.");
        // TODO: 중복 판별도 Validator 를 사용해서 할 수 있는지 확인필요함. 얘만 혼자 Repository 라서 신경쓰임
        if (postRepository.isTitleDuplicated(title)) throw new IllegalArgumentException("이미 존재하는 제목입니다.");
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }
}
