package org.sopt.exception;

public class TitleLengthException extends CustomException {
    public TitleLengthException() {
        super(Error.TITLE_LENGTH_ERROR);
    }
}
