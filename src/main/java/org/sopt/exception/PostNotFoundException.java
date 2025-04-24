package org.sopt.exception;

public class PostNotFoundException extends CustomException {
    public PostNotFoundException() {
        super(Error.POST_NOT_FOUND_ERROR);
    }
}
