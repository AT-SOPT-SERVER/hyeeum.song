package org.sopt.exception;

public class TitleBlankException extends CustomException {
    public TitleBlankException() {
        super(Error.TITLE_BLANK_ERROR);
    }
}
