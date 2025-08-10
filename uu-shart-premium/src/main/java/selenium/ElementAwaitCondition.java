package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ElementAwaitCondition {
    boolean doesElementPass(WebElement element);
}
