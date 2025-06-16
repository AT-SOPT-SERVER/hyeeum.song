package org.sopt.dto.Response;

import org.sopt.domain.Post;

import java.util.List;

public record PostListResponse(List<PostResponse> postList) {
    public static PostListResponse of(List<Post> postList) {
        List<PostResponse> convertedPostList = postList.stream()
                .map(PostResponse::from)
                .toList();

        return new PostListResponse(convertedPostList);
    }
}
