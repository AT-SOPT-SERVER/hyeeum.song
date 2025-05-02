package org.sopt.exception;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(Error.USER_NOT_FOUND_ERROR);
    }
}
