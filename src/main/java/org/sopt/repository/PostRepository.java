package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT post FROM Post post WHERE post.title LIKE %:keyword%")
    List<Post> searchPostsByKeyword(@Param("keyword") final String keyword);
    //findByTitleContaining(String keyword) → 자동으로 SQL 쿼리 생성
}
