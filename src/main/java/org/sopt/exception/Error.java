package org.sopt.exception;

public enum Error {
    TITLE_BLANK_ERROR("제목을 한 글자 이상 입력하지 않았습니다"),
    TITLE_LENGTH_ERROR("제목은 30글자 이상 입력할 수 없습니다"),
    TITLE_DUPLICATED_ERROR("이미 존재하는 제목입니다"),
    TIME_STAMP_LIMIT_ERROR("마지막 게시글 작성시간에서 3분이 지나지 않았습니다");

    private final String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
