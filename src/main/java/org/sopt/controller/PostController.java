package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class PostController {
    private final PostService postService = new PostService();

    @PostMapping("/post")
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.getTitle());
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
