package org.sopt.response;

import lombok.Getter;

@Getter
public enum Response {
    // 200 Ok
    OK(200, "응답 성공"),
    // 201 Created
    CREATED(201, "응답 성공");

    private final int responseCode;
    private final String responseMessage;

    Response(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

}
