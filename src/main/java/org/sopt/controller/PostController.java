package org.sopt.controller;

import org.sopt.constant.PathConstant;
import org.sopt.dto.Request.CommentRequest;
import org.sopt.dto.Request.PagingDto;
import org.sopt.dto.Request.PostRequest;
import org.sopt.dto.Request.TitleRequest;
import org.sopt.dto.Response.PagingPostListResponse;
import org.sopt.dto.Response.PostListResponse;
import org.sopt.dto.Response.PostResponse;
import org.sopt.response.ApiResponse;
import org.sopt.response.Response;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstant.POSTS)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ApiResponse<Void> createPost(
            @RequestHeader Long userId,
            @RequestBody final PostRequest postRequest
    ) {
        postService.createPost(userId, postRequest.title(), postRequest.content());
        return ApiResponse.successWithNoData(Response.CREATED);
    }

    @GetMapping
    public ApiResponse<PagingPostListResponse> getAllPosts(
            @RequestBody final PagingDto pagingDto
    ) {
        return ApiResponse.success(Response.OK,
                PagingPostListResponse.of(postService.getAllPosts(
                        pagingDto.pageNumber() - 1,
                        pagingDto.size(),
                        pagingDto.sort(),
                        pagingDto.standard()
                )));
    }

    @GetMapping(PathConstant.POST_ID)
    public ApiResponse<PostResponse> getPostById(@PathVariable(PathConstant.PATH_POST_ID) final long id) {
        return ApiResponse.success(Response.OK, PostResponse.from(postService.findPostById(id)));
    }

    @DeleteMapping(PathConstant.POST_ID)
    public ApiResponse<Void> deletePostById(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) final long id) {
        postService.deletePostById(userId, id);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @PutMapping(PathConstant.POST_ID)
    public ApiResponse<Void> updatePostTitle(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) final long updateId,
            @RequestBody final TitleRequest titleRequest) {
        postService.updatePostTitle(userId, updateId, titleRequest.title());
        return ApiResponse.successWithNoData(Response.OK);
    }

    @GetMapping(PathConstant.SEARCH)
    public ApiResponse<PostListResponse> searchPostsByKeyword(
            @RequestParam(PathConstant.PARAM_KEYWORD) final String keyword) {
        return ApiResponse.success(Response.OK,
                PostListResponse.of(postService.searchPostsByKeyword(keyword)));
    }

    @PostMapping(PathConstant.POST_ID + PathConstant.COMMENTS)
    public ApiResponse<Void> createComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @RequestBody final CommentRequest commentRequest
    ) {
        postService.createComment(userId, postId, commentRequest.content());
        return ApiResponse.successWithNoData(Response.CREATED);
    }

    @DeleteMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID)
    public ApiResponse<Void> deleteComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        postService.deleteComment(userId, postId, commentId);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @PutMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID)
    public ApiResponse<Void> updateComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId,
            @RequestBody final CommentRequest commentRequest
    ) {
        postService.updateComment(userId, postId, commentId, commentRequest.content());
        return ApiResponse.successWithNoData(Response.OK);
    }

    @PostMapping(PathConstant.POST_ID + PathConstant.LIKES)
    public ApiResponse<Void> likePost(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId
    ) {
        postService.likePost(userId, postId);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @DeleteMapping(PathConstant.POST_ID + PathConstant.LIKES)
    public ApiResponse<Void> unlikePost(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId
    ) {
        postService.unlikePost(userId, postId);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @PostMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID + PathConstant.LIKES)
    public ApiResponse<Void> likeComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        postService.likeComment(userId, postId, commentId);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @DeleteMapping(PathConstant.POST_ID + PathConstant.COMMENTS + PathConstant.COMMENT_ID + PathConstant.LIKES)
    public ApiResponse<Void> unlikeComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        postService.unlikeComment(userId, postId, commentId);
        return ApiResponse.successWithNoData(Response.OK);
    }
}
