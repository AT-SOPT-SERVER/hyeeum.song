package org.sopt.dto.Response;

import org.sopt.domain.Comment;

public record CommentResponse(
    String content,
    String author
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
            comment.getContent(),
            comment.getUser().getAuthor()
        );
    }
}
