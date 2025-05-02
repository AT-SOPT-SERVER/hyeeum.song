package org.sopt.exception;

public class NotPostUserErrorException extends CustomException {
    public NotPostUserErrorException() {
        super(Error.NOT_POST_USER_ERROR);
    }
}
