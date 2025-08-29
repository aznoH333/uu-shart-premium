package utils;

import java.time.LocalDateTime;

public class Logger {
    private static String log = "";

    public static void logMessage(String message) {
        Logger.log += "[ " + LocalDateTime.now() + " ] : " + message + "\n";
    }

    public static String getLog() {
        return log;
    }
}
