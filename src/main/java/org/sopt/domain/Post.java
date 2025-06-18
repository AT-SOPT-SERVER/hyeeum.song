package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.constant.DataBaseConstant;

import java.util.List;

@Entity
@Table(name = DataBaseConstant.POST)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isLiked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DataBaseConstant.USER_ID)
    private User user;

    @OneToMany(mappedBy = DataBaseConstant.POST, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Post() {

    }

    public Post(
            final String title,
            final String content,
            final User user
    ) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getIsLiked() {
        return this.isLiked;
    }

    public User getUser() {
        return this.user;
    }

    public void updateTitle(final String newTitle) {
        this.title = newTitle;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void like() {
        this.isLiked = true;
    }

    public void unlike() {
        this.isLiked = false;
    }
}
