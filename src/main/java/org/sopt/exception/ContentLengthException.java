package org.sopt.exception;

public class ContentLengthException extends CustomException {
    public ContentLengthException(int limit) {
        super(Error.CONTENT_LENGTH_ERROR, limit);
    }
}
