package org.sopt.util;

import java.time.LocalDateTime;

public class LastTimeStampGeneratorUtil {
    private static LocalDateTime lastTimeStamp;

    public static void setLastTimeStamp() {
        lastTimeStamp = LocalDateTime.now();
    }

    public static LocalDateTime getLastTimeStamp() {
        return lastTimeStamp;
    }
}
