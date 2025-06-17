package org.sopt.exception;

public class NotUserErrorException extends CustomException {
    public NotUserErrorException() {
        super(Error.NOT_USER_ERROR);
    }
}
