package sites.unicorn;

import selenium.ElementAwaitCondition;

public class UnicornElementConditions {
    public static ElementAwaitCondition HAS_TEXT_OR_IMAGE = (element)-> element.getSize().width != 0;
}
