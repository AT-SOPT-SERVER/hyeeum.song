package org.sopt.controller;

import org.sopt.constant.UriConstant;
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

    @PostMapping(UriConstant.POSTS)
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.getTitle());
    }

    @GetMapping(UriConstant.POSTS)
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping(UriConstant.POST_BY_ID_URI)
    public ResponseEntity<?> getPostById(@PathVariable("id") final long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @DeleteMapping(UriConstant.POST_BY_ID_URI)
    public ResponseEntity<?> deletePostById(@PathVariable("id") final long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }

    @PutMapping(UriConstant.POST_BY_ID_URI)
    public ResponseEntity<?> updatePostTitle(@PathVariable("id") final long updateId, @RequestBody String newTitle) {
        return ResponseEntity.ok(postService.updatePostTitle(updateId, newTitle));
    }

    @GetMapping(UriConstant.SEARCH_POSTS_URI)
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam("keyword") final String keyword) {
        return ResponseEntity.ok(postService.searchPostsByKeyword(keyword));
    }
}
