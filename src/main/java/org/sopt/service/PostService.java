package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import validator.TitleValidator;

import java.util.List;

//레포지토리를 가져와서 사용하는 역할 (저장소 가져오기) - 로직
public class PostService {
    private PostRepository postRepository = new PostRepository();
    static final int TITLE_LENGTH_LIMIT = 30;

    public void createPost(Post post) {
        validateTitle(post.getTitle());

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

    public void validateTitle(String title) {
        if (TitleValidator.isTitleBlank(title)) throw new IllegalArgumentException("제목은 한 글자 이상 입력해야합니다.");
        if (TitleValidator.isTitleExceedsLength(title, TITLE_LENGTH_LIMIT))
            throw new IllegalArgumentException("제목은 30글자 이하만 입력 가능합니다.");
    }
}
