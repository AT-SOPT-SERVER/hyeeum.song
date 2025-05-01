package org.sopt.exception;

public class PostTimeLimitException extends CustomException {
    public PostTimeLimitException(int limit) {
        super(Error.POST_TIME_LIMIT_ERROR, limit);
    }
}
