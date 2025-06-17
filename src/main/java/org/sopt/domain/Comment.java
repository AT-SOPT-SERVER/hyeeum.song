package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.constant.DataBaseConstant;

@Entity
@Table(name = DataBaseConstant.COMMENT)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DataBaseConstant.POST_ID)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DataBaseConstant.USER_ID)
    private User user;

    public Comment() {

    }

    public Comment(
            final String content,
            final User user,
            final Post post
    ) {
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public String getContent() {
        return this.content;
    }

    public User getUser() {
        return this.user;
    }

    public Post getPost() {
        return this.post;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
