package org.sopt.response;

public enum Response {
    // 200 Ok
    OK(200, "응답 성공"),
    // 201 Created
    CREATED(201, "응답 성공");

    private final long responseCode;
    private final String responseMessage;

    Response(long responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public long getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
