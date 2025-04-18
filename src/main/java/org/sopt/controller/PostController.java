package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;
import java.util.Optional;

public class PostController {
    private final PostService postService = new PostService();

    public void createPost(String title) {
        postService.createPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Optional<Post> getPostById(final long id) {
        return postService.findPostById(id);
    }

    public boolean deletePostById(final long id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(final long updateId, String newTitle) {
        return postService.updatePostTitle(updateId, newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
