package org.sopt.validator;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.constant.PostConstant.POST_TIME_LIMIT;
import static org.sopt.exception.Error.TIME_STAMP_LIMIT_ERROR;

public class TimeStampValidator {
    public static void validateLastTimeStampLimit(LocalDateTime lastTimeStamp, int limitTime) {
        Duration duration = Duration.between(lastTimeStamp, LocalDateTime.now());
        if (duration.toMinutes() < limitTime)
            throw new IllegalArgumentException(TIME_STAMP_LIMIT_ERROR.getErrorMessage(POST_TIME_LIMIT));
    }
}
