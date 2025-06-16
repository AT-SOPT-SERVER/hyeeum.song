package org.sopt.validator;

public class ContentValidator {
    public static boolean isContentBlank(final String content) {
        return content == null || content.isBlank();
    }

    public static boolean isContentExceedsLength(final String content, final int limitLength) {
        return (content.length() > limitLength);
    }
}
