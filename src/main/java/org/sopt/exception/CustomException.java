package org.sopt.exception;

public class CustomException extends RuntimeException {
    private final Error code;

    public CustomException(Error error) {
        super(error.getErrorMessage());
        this.code = error;
    }

    public Error getCode() {
        return code;
    }
}
