package org.sopt.controller;

import org.sopt.constant.UriConstant;
import org.sopt.dto.PostRequest;
import org.sopt.dto.TitleUpdateRequest;
import org.sopt.response.Response;
import org.sopt.service.PostService;
import org.sopt.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(UriConstant.POSTS)
    public ResponseEntity<?> createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.title());
        return ApiUtil.success(Response.CREATED, null);
    }

    @GetMapping(UriConstant.POSTS)
    public ResponseEntity<?> getAllPosts() {
        return ApiUtil.success(Response.OK, postService.getAllPosts());
    }

    @GetMapping(UriConstant.POST_BY_ID_URI)
    public ResponseEntity<?> getPostById(@PathVariable("id") final long id) {
        return ApiUtil.success(Response.OK, postService.findPostById(id));
    }

    @DeleteMapping(UriConstant.POST_BY_ID_URI)
    public ResponseEntity<?> deletePostById(@PathVariable("id") final long id) {
        return ApiUtil.success(Response.OK, postService.deletePostById(id));
    }

    @PutMapping(UriConstant.POST_BY_ID_URI)
    public ResponseEntity<?> updatePostTitle(@PathVariable("id") final long updateId, @RequestBody TitleUpdateRequest titleUpdateRequest) {
        return ApiUtil.success(Response.OK, postService.updatePostTitle(updateId, titleUpdateRequest.title()));
    }

    @GetMapping(UriConstant.SEARCH_POSTS_URI)
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam("keyword") final String keyword) {
        return ApiUtil.success(Response.OK, postService.searchPostsByKeyword(keyword));
    }
}
