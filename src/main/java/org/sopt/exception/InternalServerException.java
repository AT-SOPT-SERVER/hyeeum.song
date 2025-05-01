package org.sopt.exception;

public class InternalServerException extends CustomException {
    public InternalServerException() {
        super(Error.INTERNAL_SERVER_ERROR);
    }
}
