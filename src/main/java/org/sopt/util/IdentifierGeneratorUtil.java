package org.sopt.util;

public class IdentifierGeneratorUtil {
    private static long postId = 1;

    public synchronized static long generateIdentifier() {
        return postId++;
    }
}
