package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(final Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Optional<Post> findPostById(final long id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public boolean deletePostById(final long id) {
        return postList.removeIf(post -> post.getId() == id);
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postList.stream()
                .filter(post -> post.getTitle().contains(keyword))
                .collect(Collectors.toList());
    }

    public boolean isTitleDuplicated(final String title) {
        for (Post postList : postList) {
            if (title.equals(postList.getTitle()))
                return true;
        }
        return false;
    }
}
