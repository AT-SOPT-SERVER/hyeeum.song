package org.sopt.exception;

public class CustomException extends RuntimeException {
    private final Error code;
    private final Object[] args;

    public CustomException(Error error, Object... args) {
        super(error.getErrorMessage(args));
        this.code = error;
        this.args = args;
    }

    public Error getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }
}
