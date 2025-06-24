package org.sopt.controller;

import org.sopt.constant.PathConstant;
import org.sopt.dto.Request.CommentRequest;
import org.sopt.response.ApiResponse;
import org.sopt.response.Response;
import org.sopt.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstant.POSTS + PathConstant.POST_ID + PathConstant.COMMENTS)
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ApiResponse<Void> createComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @RequestBody CommentRequest commentRequest
    ) {
        commentService.createComment(userId, postId, commentRequest.content());
        return ApiResponse.successWithNoData(Response.CREATED);
    }

    @DeleteMapping(PathConstant.COMMENT_ID)
    public ApiResponse<Void> deleteComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        commentService.deleteComment(userId, postId, commentId);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @PutMapping(PathConstant.COMMENT_ID)
    public ApiResponse<Void> updateComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId,
            @RequestBody CommentRequest commentRequest
    ) {
        commentService.updateComment(userId, postId, commentId, commentRequest.content());
        return ApiResponse.successWithNoData(Response.OK);
    }

    @PostMapping(PathConstant.COMMENT_ID + PathConstant.LIKES)
    public ApiResponse<Void> likeComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        commentService.likeComment(userId, postId, commentId);
        return ApiResponse.successWithNoData(Response.OK);
    }

    @DeleteMapping(PathConstant.COMMENT_ID + PathConstant.LIKES)
    public ApiResponse<Void> unlikeComment(
            @RequestHeader Long userId,
            @PathVariable(PathConstant.PATH_POST_ID) Long postId,
            @PathVariable(PathConstant.PATH_COMMENT_ID) Long commentId
    ) {
        commentService.unlikeComment(userId, postId, commentId);
        return ApiResponse.successWithNoData(Response.OK);
    }
}
