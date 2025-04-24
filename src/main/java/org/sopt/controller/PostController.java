package org.sopt.controller;

import org.sopt.dto.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.getTitle());
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") final long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable("id") final long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePostTitle(@PathVariable("id") final long updateId, @RequestBody String newTitle) {
        return ResponseEntity.ok(postService.updatePostTitle(updateId, newTitle));
    }

    @GetMapping("posts/search")
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam("keyword") final String keyword) {
        return ResponseEntity.ok(postService.searchPostsByKeyword(keyword));
    }
}
