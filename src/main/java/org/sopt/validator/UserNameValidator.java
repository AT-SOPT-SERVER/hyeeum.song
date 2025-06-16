package org.sopt.validator;

// interface 로 구현해도 괜찮을 것 같다!
public class UserNameValidator {
    public static boolean isUserNameBlank(final String userName) {
        return userName == null || userName.isBlank();
    }

    public static boolean isUserNameExceedsLength(final String userName, final int limitLength) {
        return (userName.length() > limitLength);
    }
}
