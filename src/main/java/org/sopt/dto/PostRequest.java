package org.sopt.dto;

public class PostRequest {
    private String title;

    public PostRequest() {

    }

    public PostRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
