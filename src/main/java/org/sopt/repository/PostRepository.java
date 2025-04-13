package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//게시글 저장,삭제 책임 (저장소)
public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public boolean deletePostById(int id) {
        return postList.removeIf(post -> post.getId() == id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postList.stream()
                .filter(post -> post.getTitle().contains(keyword))
                .collect(Collectors.toList());
    }

    public boolean isTitleDuplicated(String title) {
        for (Post postList : postList) {
            if (title.equals(postList.getTitle()))
                return true;
        }
        return false;
    }
}
