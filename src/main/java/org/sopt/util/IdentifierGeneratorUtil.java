package org.sopt.util;

public class IdentifierGeneratorUtil {
    private static int postID = 1;

    public static int generateIdentifier() {
        return postID++;
    }
}
