package org.sopt.exception;

import org.sopt.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> handleCustomException(CustomException error) {
        return ApiResponse.failureWithArgs(error.getCode(), error.getArgs());
    }

    // 404 Not Found - 지원하지 않는 URL
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponse<Void> handleNotFoundUrl(NoHandlerFoundException e) {
        return ApiResponse.failure(Error.INVALID_URL_ERROR);
    }

    // 405 Method Not Allowed - 잘못된 HTTP method 요청
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Void> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ApiResponse.failure(Error.METHOD_NOT_ALLOWED_ERROR);
    }

    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleInternalServerError(Exception e) {
        return ApiResponse.failure(Error.INTERNAL_SERVER_ERROR);
    }
}
