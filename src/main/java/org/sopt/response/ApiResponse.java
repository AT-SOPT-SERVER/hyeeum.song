package org.sopt.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.exception.Error;

import java.util.Arrays;

public record ApiResponse<T>(
        int status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static <T> ApiResponse<T> success(Response successCode, T data) {
        return new ApiResponse<>(successCode.getResponseCode(), successCode.getResponseMessage(), data);
    }

    public static <T> ApiResponse<T> successWithNoData(Response successCode) {
        return new ApiResponse<>(successCode.getResponseCode(), successCode.getResponseMessage(), null);
    }

    public static <T> ApiResponse<T> failure(Error errorCode) {
        return new ApiResponse<>(errorCode.getErrorCode(), errorCode.getErrorMessage(), null);
    }

    public static <T> ApiResponse<T> failureWithArgs(Error errorCode, Object[] errorMessage) {
        return new ApiResponse<>(errorCode.getErrorCode(), Arrays.toString(errorMessage), null);
    }
}
