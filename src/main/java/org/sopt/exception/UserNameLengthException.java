package org.sopt.exception;

public class UserNameLengthException extends CustomException {
    public UserNameLengthException(int limit) {
        super(Error.USER_NAME_LENGTH_ERROR, limit);
    }
}
