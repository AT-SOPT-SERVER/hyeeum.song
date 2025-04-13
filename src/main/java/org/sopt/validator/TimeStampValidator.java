package org.sopt.validator;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeStampValidator {
    public static void validateLastTimeStampLimit(LocalDateTime lastTimeStamp, int limitTime) {
        Duration duration = Duration.between(lastTimeStamp, LocalDateTime.now());
        if (duration.toMinutes() < limitTime)
            throw new IllegalArgumentException("새 게시글은 3분이 지나고 난 후에 작성 가능합니다.");
    }
}
