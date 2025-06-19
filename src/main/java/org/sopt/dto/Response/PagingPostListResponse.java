package org.sopt.dto.Response;

import org.sopt.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public record PagingPostListResponse(
        int totalPageNumber,
        int currentPageNumber,
        List<PostResponse> postList
) {
    public static PagingPostListResponse of(Page<Post> postList) {
        List<PostResponse> convertedPostList = postList.getContent()
                .stream()
                .map(PostResponse::from)
                .toList();

        return new PagingPostListResponse(
                postList.getTotalPages(),
                postList.getNumber() + 1,
                convertedPostList
        );
    }
}
