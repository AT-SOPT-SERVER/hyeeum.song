package validator;

public class TitleValidator {

    // 제목이 비어있는 경우 처리
    public static boolean isTitleBlank(String title) {
        return title.isBlank();
    }

    // 제목이 30자 이상인 경우 처리
    public static boolean isTitleExceedsLength(String title, int limitLength) {
        return (title.length() > limitLength);
    }
}
