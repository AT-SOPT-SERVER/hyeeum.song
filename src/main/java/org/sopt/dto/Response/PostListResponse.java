package org.sopt.dto.Response;

import org.sopt.domain.Post;

import java.util.List;

public record PostListResponse(List<Post> postList) {
    public static PostListResponse of(List<Post> postList) {
        return new PostListResponse(postList);
    }
}
