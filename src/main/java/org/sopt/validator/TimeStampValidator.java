package org.sopt.validator;

import org.sopt.exception.PostTimeLimitException;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.sopt.constant.PostConstant.POST_TIME_LIMIT;

public class TimeStampValidator {
    public static void validateLastTimeStampLimit(final LocalDateTime lastTimeStamp, final int limitTime) {
        Duration duration = Duration.between(lastTimeStamp, LocalDateTime.now());
        if (duration.toMinutes() < limitTime)
            throw new PostTimeLimitException(POST_TIME_LIMIT);
    }
}
