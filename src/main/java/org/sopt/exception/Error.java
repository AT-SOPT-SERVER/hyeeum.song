package org.sopt.exception;

import lombok.Getter;

public enum Error {
    // 400 Bad Request
    TITLE_BLANK_ERROR(40001, "제목은 비어있을 수 없습니다."),
    TITLE_LENGTH_ERROR(40002, "제목의 길이는 %d자를 넘을 수 없습니다."),
    POST_TIME_LIMIT_ERROR(40003, "마지막 게시글 작성 시간 %d분 후에 작성이 가능합니다."),
    NOT_USER_ERROR(40004, "작성자만 수정가능합니다."),
    CONTENT_BLANK_ERROR(40005, "내용은 비어있을 수 없습니다."),
    CONTENT_LENGTH_ERROR(40006, "내용의 길이는 %d자를 넘을 수 없습니다."),
    USER_NAME_BLANK_ERROR(40007, "작성자의 이름은 비어있을 수 없습니다."),
    USER_NAME_LENGTH_ERROR(40008, "작성자의 이름은 %d자를 넘을 수 없습니다."),

    // 404 Not Found
    INVALID_URL_ERROR(40401, "지원하지 않는 URL입니다."),
    POST_NOT_FOUND_ERROR(40402, "존재하지 않는 게시물입니다."),
    USER_NOT_FOUND_ERROR(40403, "존재하지 않는 사용자입니다."),
    COMMENT_NOT_FOUND_ERROR(40404, "존재하지 않는 댓글입니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED_ERROR(40501, "잘못된 HTTP method 요청입니다."),

    // 409 Conflict
    TITLE_DUPLICATED_ERROR(40901, "중복된 게시글 제목입니다."),
    POST_LIKE_DUPLICATED_ERROR(40902, "이미 좋아요한 게시글입니다."),
    COMMENT_LIKE_DUPLICATED_ERROR(40903, "이미 좋아요한 댓글입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50001, "서버 내부 오류입니다.");

    @Getter
    private final int errorCode;
    private final String errorMessage;

    Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(final Object... args) {
        return String.format(errorMessage, args);
    }
}
