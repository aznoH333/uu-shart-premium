package utils;

public class TextUtils {
    public static String removeBreaks(String text) {
        return text
                .replace("\n", "")
                .replace("\r", "")
                .replace("\t", "");
    }
}
