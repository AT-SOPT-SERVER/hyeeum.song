package org.sopt.util;

import org.sopt.exception.Error;
import org.sopt.response.ApiResponse;
import org.sopt.response.Response;
import org.springframework.http.ResponseEntity;

public class ApiUtil {
    public static <T> ResponseEntity<ApiResponse<T>> successWithNoData(Response response) {
        return ResponseEntity.status((int) response.getResponseCode())
                .body(ApiResponse.of(response, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(Response response, T data) {
        return ResponseEntity.status((int) response.getResponseCode())
                .body(ApiResponse.of(response, data));
    }

    public static ResponseEntity<ApiResponse<Void>> error(Error error, Object... args) {
        return ResponseEntity
                .status((int) (error.getErrorCode() / 100))
                .body(new ApiResponse<>(error.getErrorCode(), error.getErrorMessage(args), null));
    }
}

