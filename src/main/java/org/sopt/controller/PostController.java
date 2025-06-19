package org.sopt.controller;

import org.sopt.constant.PathConstant;
import org.sopt.dto.Request.CommentRequest;
import org.sopt.dto.Request.PagingDto;
import org.sopt.dto.Request.PostRequest;
import org.sopt.dto.Request.TitleRequest;
import org.sopt.dto.Response.PagingPostListResponse;
import org.sopt.dto.Response.PostListResponse;
import org.sopt.dto.Response.PostResponse;
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
        postService.createPost(userId, postRequest.title(), postRequest.content());
        return ApiUtil.successWithNoData(Response.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(
            @RequestBody final PagingDto pagingDto
    ) {
        return ApiUtil.success(Response.OK,
                PagingPostListResponse.of(postService.getAllPosts(
                                pagingDto.pageNumber() - 1,
                                pagingDto.size(),
                                pagingDto.sort(),
                                pagingDto.standard()
                        )
                )
        );
    }

    @GetMapping(PathConstant.POST_ID)
    public ResponseEntity<?> getPostById(@PathVariable(PathConstant.PATH_POST_ID) final long id) {
        return ApiUtil.success(Response.OK, PostResponse.from(postService.findPostById(id)));
    }

    @DeleteMapping(PathConstant.POST_ID)
    public ResponseEntity<?> deletePostById(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) final long id) {
        postService.deletePostById(userId, id);
        return ApiUtil.successWithNoData(Response.OK);
    }

    @PutMapping(PathConstant.POST_ID)
    public ResponseEntity<?> updatePostTitle(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) final long updateId, final @RequestBody TitleRequest titleRequest) {
        postService.updatePostTitle(userId, updateId, titleRequest.title());
        return ApiUtil.successWithNoData(Response.OK);
    }

    @GetMapping(PathConstant.SEARCH)
    public ResponseEntity<?> searchPostsByKeyword(@RequestParam(PathConstant.PARAM_KEYWORD) final String keyword) {
        return ApiUtil.success(Response.OK, PostListResponse.of(postService.searchPostsByKeyword(keyword)));
    }

    @PostMapping(PathConstant.POST_ID + PathConstant.COMMENTS)
    public ResponseEntity<?> createComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @RequestBody final CommentRequest commentRequest
    ) {
        postService.createComment(userId, postId, commentRequest.content());
        return ApiUtil.successWithNoData(Response.CREATED);
    }

    @DeleteMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID)
    public ResponseEntity<?> deleteComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        postService.deleteComment(userId, postId, commentId);
        return ApiUtil.successWithNoData(Response.CREATED);
    }

    @PutMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID)
    public ResponseEntity<?> updateComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId,
            @RequestBody final CommentRequest commentRequest
    ) {
        postService.updateComment(userId, postId, commentId, commentRequest.content());
        return ApiUtil.successWithNoData(Response.OK);
    }

    @PostMapping(PathConstant.POST_ID + PathConstant.LIKES)
    public ResponseEntity<?> likePost(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId
    ) {
        postService.likePost(userId, postId);
        return ApiUtil.successWithNoData(Response.OK);
    }

    @DeleteMapping(PathConstant.POST_ID + PathConstant.LIKES)
    public ResponseEntity<?> unlikePost(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId
    ) {
        postService.unlikePost(userId, postId);
        return ApiUtil.successWithNoData(Response.OK);
    }

    @PostMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID + PathConstant.LIKES)
    public ResponseEntity<?> likeComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        postService.likeComment(userId, postId, commentId);
        return ApiUtil.successWithNoData(Response.OK);
    }

    @DeleteMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID + PathConstant.LIKES)
    public ResponseEntity<?> unlikeComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        postService.unlikeComment(userId, postId, commentId);
        return ApiUtil.successWithNoData(Response.OK);
    }
}
