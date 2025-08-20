package selenium;

import org.openqa.selenium.By;

public class ByUtils {
    public static By compoundClass(String className) {
        return By.cssSelector("." + className.replace(" ", "."));
    }

    public static By classNameParentsOnly(String className) {
        return By.xpath(".//*[contains(@class, '" + className +"') and not (ancestor::*[contains(@class,'" + className + "')])]");
    }

    public static By classNameWithText(String className) {
        return By.xpath(".//*[contains(@class, '" + className +"') and string()]");
    }
}
