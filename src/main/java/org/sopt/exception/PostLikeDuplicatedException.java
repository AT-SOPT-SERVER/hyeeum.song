package org.sopt.exception;

public class PostLikeDuplicatedException extends CustomException {
    public PostLikeDuplicatedException() {
        super(Error.POST_LIKE_DUPLICATED_ERROR);
    }
}
