package org.sopt.domain;

public class Post {
    private int id;
    private String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
