package org.sopt.exception;

public class TitleDuplicatedException extends CustomException {
    public TitleDuplicatedException() {
        super(Error.TITLE_DUPLICATED_ERROR);
    }
}
