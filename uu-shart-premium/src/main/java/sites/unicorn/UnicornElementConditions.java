package sites.unicorn;

import org.openqa.selenium.Dimension;
import selenium.ElementAwaitCondition;

public class UnicornElementConditions {
    public static ElementAwaitCondition HAS_TEXT_OR_IMAGE = (element)->{
        Dimension size = element.getSize();

        return !element.getText().isEmpty() || size.width > 120;
    };
}
