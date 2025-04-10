package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;
import org.sopt.util.IdentifierGeneratorUtil;
import org.sopt.util.LastTimeStampGeneratorUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

//요청에 따라 서비스를 결정 - 요청을 받음
public class PostController {
    private PostService postService = new PostService();

    public void createPost(String title) {
        // TODO: 3분이 지났는지 판별하는 로직을 어디에 두어야할까? 흐음
        LocalDateTime currentTime = LocalDateTime.now();

        if (LastTimeStampGeneratorUtil.getLastTimeStamp() == null) {
            Post post = new Post(IdentifierGeneratorUtil.generateIdentifier(), title);
            LastTimeStampGeneratorUtil.setLastTimeStamp(currentTime);
            postService.createPost(post);
        } else {
            Duration duration = Duration.between(LastTimeStampGeneratorUtil.getLastTimeStamp(), currentTime);
            if (duration.toMinutes() < 3)
                throw new IllegalArgumentException("새 게시글은 3분이 지나고 난 후에 작성 가능합니다.");
        }
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        return postService.updatePostTitle(updateId, newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
