package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUtils {
    public static void writeFile(String fileName, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            Logger.logMessage("Failed to write to file: " + fileName + " " + e.getMessage());
        }
    }
}
