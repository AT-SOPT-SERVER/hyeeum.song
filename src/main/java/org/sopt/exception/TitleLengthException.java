package org.sopt.exception;

public class TitleLengthException extends CustomException {
    public TitleLengthException(int limit) {
        super(Error.TITLE_LENGTH_ERROR, limit);
    }
}
