package org.sopt.exception;

public class InvalidUrlException extends CustomException {
    public InvalidUrlException() {
        super(Error.INVALID_URL_ERROR);
    }
}
