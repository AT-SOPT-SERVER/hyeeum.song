package org.sopt.exception;

public class CustomException extends RuntimeException {
    private final Error code;

    public CustomException(Error error, Object... args) {
        super(error.getErrorMessage(args));
        this.code = error;
    }

    public Error getCode() {
        return code;
    }
}
