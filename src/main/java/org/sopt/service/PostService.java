package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

//레포지토리를 가져와서 사용하는 역할 (저장소 가져오기) - 로직
public class PostService {
    private PostRepository postRepository = new PostRepository();

    public void createPost(Post post) {
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
        Post post = postRepository.findPostById(updateId);
        if (post == null) return false;

        post.setTitle(newTitle);
        return true;
    }
}
