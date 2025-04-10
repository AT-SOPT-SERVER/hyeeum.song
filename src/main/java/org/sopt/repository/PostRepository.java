package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

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
        for (Post post : postList) {
            if (post.getId() == id) {
//              postList.remove(postList.indexOf(post));
                postList.remove(post);
                return true;
            }
        }
        return false;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> searchedPostList = new ArrayList<>(); // 메모리 잡아먹을 듯함

        for (Post post : postList) {
            if (post.getTitle().contains(keyword)) {
                searchedPostList.add(post);
            }
        }
        return searchedPostList;
    }

    public boolean isTitleDuplicated(String title) {
        for (Post postList : postList) {
            if (title.equals(postList.getTitle()))
                return true;
        }
        return false;
    }
}
