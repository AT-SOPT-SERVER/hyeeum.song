package org.sopt.exception;

public class CommentNotFoundException extends CustomException {
    public CommentNotFoundException() {
        super(Error.COMMENT_NOT_FOUND_ERROR);
    }
}
