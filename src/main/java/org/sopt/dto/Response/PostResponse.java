package org.sopt.dto.Response;

import org.sopt.domain.Post;

public record PostResponse(
    String title,
    String content,
    String author
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
            post.getTitle(),
            post.getContent(),
            post.getUser().getAuthor()
        );
    }
}
