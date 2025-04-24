package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.getTitle());
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPostById(@PathVariable("id") final long id) {
        return postService.findPostById(id);
    }

    @DeleteMapping("/post/{id}")
    public void deletePostById(@PathVariable("id") final long id) {
        postService.deletePostById(id);
    }

    @PutMapping("/post/{id}")
    public boolean updatePostTitle(@PathVariable("id") final long updateId, @RequestBody String newTitle) {
        return postService.updatePostTitle(updateId, newTitle);
    }

    @GetMapping("post/search")
    public List<Post> searchPostsByKeyword(@RequestParam("keyword") final String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
