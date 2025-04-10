package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;
import org.sopt.util.IdentifierGeneratorUtil;

import java.util.List;

//요청에 따라 서비스를 결정 - 요청을 받음
public class PostController {
    private PostService postService = new PostService();

    public void createPost(String title) {
        Post post = new Post(IdentifierGeneratorUtil.generateIdentifier(), title);
        postService.createPost(post);
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
