package org.sopt.exception;

public class ContentBlankException extends CustomException {
    public ContentBlankException() {
        super(Error.CONTENT_BLANK_ERROR);
    }
}
