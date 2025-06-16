package org.sopt.exception;

public class UserNameBlankException extends CustomException {
    public UserNameBlankException() {
        super(Error.USER_NAME_BLANK_ERROR);
    }
}
