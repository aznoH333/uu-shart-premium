package selenium;

import org.openqa.selenium.By;

public class ByUtils {
    public static By compoundClass(String className) {
        return By.cssSelector("." + className.replace(" ", "."));
    }
}
