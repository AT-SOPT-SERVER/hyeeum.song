package org.sopt.util;

import java.time.LocalDateTime;

public class LastTimeStampGeneratorUtil {
    private static LocalDateTime lastTimeStamp;

    public static void setLastTimeStamp(LocalDateTime lastTimeStamp) {
        LastTimeStampGeneratorUtil.lastTimeStamp = lastTimeStamp;
    }

    public static LocalDateTime getLastTimeStamp() {
        return lastTimeStamp;
    }
}
