package org.sopt.controller;

import org.sopt.constant.PathConstant;
import org.sopt.dto.Request.PostRequest;
import org.sopt.dto.Request.TitleRequest;
import org.sopt.dto.Response.PostListResponse;
import org.sopt.response.Response;
import org.sopt.service.PostService;
import org.sopt.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstant.POSTS)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestHeader Long userId,
            @RequestBody final PostRequest postRequest
    ) {
        postService.createPost(userId, postRequest.title());
        return ApiUtil.successWithNoData(Response.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return ApiUtil.success(Response.OK, PostListResponse.of(postService.getAllPosts()));
    }

    @GetMapping(PathConstant.ID)
    public ResponseEntity<?> getPostById(@PathVariable(PathConstant.PATH_ID) final long id) {
        return ApiUtil.success(Response.OK, postService.findPostById(id));
    }

    @DeleteMapping(PathConstant.ID)
    public ResponseEntity<?> deletePostById(@PathVariable(PathConstant.PATH_ID) final long id) {
        postService.deletePostById(id);
        return ApiUtil.successWithNoData(Response.OK);
    }

    @PutMapping(PathConstant.ID)
    public ResponseEntity<?> updatePostTitle(@PathVariable(PathConstant.PATH_ID) final long updateId, final @RequestBody TitleRequest titleRequest) {
        postService.updatePostTitle(updateId, titleRequest.title());
        return ApiUtil.successWithNoData(Response.OK);
    }

    @GetMapping(PathConstant.SEARCH)
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam(PathConstant.PARAM_KEYWORD) final String keyword) {
        return ApiUtil.success(Response.OK, PostListResponse.of(postService.searchPostsByKeyword(keyword)));
    }
}
