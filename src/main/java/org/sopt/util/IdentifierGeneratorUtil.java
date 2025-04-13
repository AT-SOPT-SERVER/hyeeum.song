package org.sopt.util;

public class IdentifierGeneratorUtil {
    private static int postId = 1;

    public static int generateIdentifier() {
        return postId++;
    }
}
