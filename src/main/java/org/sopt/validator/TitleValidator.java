package org.sopt.validator;

public class TitleValidator {

    public static boolean isTitleBlank(final String title) {
        return title == null || title.isBlank();
    }

    public static boolean isTitleExceedsLength(final String title, final int limitLength) {
        return (title.length() > limitLength);
    }
}
