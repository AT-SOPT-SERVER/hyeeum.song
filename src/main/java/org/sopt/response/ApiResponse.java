package org.sopt.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T> {
    private final long code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL) // 직렬화 조건부: null 이면 아예 응답에서 제외함
    private final T data;

    public static <T> ApiResponse<T> of(Response response, T data) {
        return new ApiResponse<>(response, data);
    }

    public ApiResponse(Response response, T data) {
        this.code = response.getResponseCode();
        this.message = response.getResponseMessage();
        this.data = data;
    }

    public ApiResponse(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
