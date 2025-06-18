package org.sopt.exception;

public class CommentLikeDuplicatedException extends CustomException {
    public CommentLikeDuplicatedException() {
        super(Error.POST_LIKE_DUPLICATED_ERROR);
    }
}
