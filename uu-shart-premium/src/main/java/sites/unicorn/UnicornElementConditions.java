package sites.unicorn;

import org.openqa.selenium.Dimension;
import selenium.ElementAwaitCondition;

public class UnicornElementConditions {
    public static ElementAwaitCondition HAS_TEXT_OR_IMAGE = (element)->{
        Dimension size = element.getSize();
        System.out.println(size.width + "x" + size.height);
        return !element.getText().isEmpty() || size.width > 120;
    };
}
