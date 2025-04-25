package org.sopt.exception;

import org.sopt.response.ApiResponse;
import org.sopt.util.ApiUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException error) {
        return ApiUtil.error(error.getCode(), error.getArgs());
    }

    // 404 Not Found - 지원하지 않는 URL
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundUrl(NoHandlerFoundException e) {
        return ApiUtil.error(Error.INVALID_URL_ERROR);
    }

    // 405 Method Not Allowed - 잘못된 HTTP method 요청
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ApiUtil.error(Error.METHOD_NOT_ALLOWED_ERROR);
    }

    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleInternalServerError(Exception e) {
        return ApiUtil.error(Error.INTERNAL_SERVER_ERROR);
    }
}
