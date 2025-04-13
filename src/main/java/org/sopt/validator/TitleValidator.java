package org.sopt.validator;

public class TitleValidator {

    public static boolean isTitleBlank(String title) {
        return title.isBlank();
    }

    public static boolean isTitleExceedsLength(String title, int limitLength) {
        return (title.length() > limitLength);
    }
}
