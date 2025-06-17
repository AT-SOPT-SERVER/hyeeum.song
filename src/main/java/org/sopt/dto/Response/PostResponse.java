package org.sopt.dto.Response;

import org.sopt.domain.Post;

import java.util.List;

public record PostResponse(
        String title,
        String content,
        String author,
        List<CommentResponse> comments
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getUser().getAuthor(),
                post.getComments().stream()
                        .map(CommentResponse::from)
                        .toList()
        );
    }
}
