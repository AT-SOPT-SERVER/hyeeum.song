package org.sopt.domain;

public class Post {
    private final long id;
    private String title;

    public Post(final long id, final String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void updateTitle(final String newTitle) {
        this.title = newTitle;
    }
}
