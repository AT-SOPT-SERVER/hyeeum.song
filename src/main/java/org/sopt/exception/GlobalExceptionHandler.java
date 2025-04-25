package org.sopt.exception;

import org.sopt.response.ApiResponse;
import org.sopt.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException error) {
        return ApiUtil.error(error.getCode(), error.getArgs());
    }
}
