package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeleniumWrapper {

    
    private int timeout;
    private int attempts;
    private final WebDriver driver;
    private final Scanner scanner;
    // click action
    private boolean useDefaultClick;
    private int clickX;
    private int clickY;
    // await condition
    ElementAwaitCondition elementAwaitCondition;

    private final static int DEFAULT_TIMEOUT = 60;
    private final static int DEFAULT_ATTEMPTS = 500;
    private final static boolean DEFAULT_USE_DEFAULT_CLICK = false;

    public SeleniumWrapper() {
        this.driver = setupWrapper();
        this.scanner = new Scanner(System.in);
        this.timeout = DEFAULT_TIMEOUT;
        this.attempts = DEFAULT_ATTEMPTS;
        this.useDefaultClick = DEFAULT_USE_DEFAULT_CLICK;
        this.clickX = 0;
        this.clickY = 0;
        elementAwaitCondition = null;
    }

    private static WebDriver setupWrapper() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/home/aznoh/.config/chromium");// TODO set profile from outside
        options.addArguments("profile-directory=Default");

        return new ChromeDriver(options);
    }

    public void goToUrl(String url) {
        this.driver.get(url);
    }

    public void awaitUserAction(String message) {
        System.out.println("User action required : " + message);
        this.scanner.nextLine();
    }
    
    public void wait(int durationMs) {
        try { Thread.sleep(durationMs); } catch (InterruptedException ignored) {}
    }

    public WebElement getElement(By by) {
        try {
            return this.driver.findElement(by);
        }catch (Exception e) {
            return null;
        }
    }

    public List<WebElement> getElements(By by) {
        try {
            return this.driver.findElements(by);
        }catch (Exception e) {
            return new ArrayList<>();
        }
    }



    public WebElement awaitElement(By by, int timeout, int attempts) {
        WebElement element = null;
        
        for (int i = 0; i < attempts; i++) {
            element = getElement(by);
            if (element != null) {
                break;
            }
            wait(timeout);
        }
        
        return element;
    }


    public WebElement awaitElement(By by) {
        return awaitElement(by, timeout, attempts);
    }

    public void waitForElement(By by) {
        WebElement element = getElement(by);

        while (element == null) {
            wait(timeout);
            element = getElement(by);
        }
    }

    private void waitForConditionToPass(By by, ElementAwaitCondition condition) {
        WebElement element = null;

        for (int i = 0; i < attempts; i++) {
            element = getElement(by);
            if (element != null && condition.doesElementPass(element)) {
                break;
            }
            wait(timeout);
        }
    }

    public void clickFirstInCollection(By by) {
        this.performClickAction(this.getElements(by).getFirst());
    }

    public void clickElementInCollection(By by, int index) {
        this.performClickAction(this.getElements(by).get(index));
    }

    public void clickLastInCollection(By by) {
        this.performClickAction(this.getElements(by).getLast());
    }


    public int countElements(By by) {
        return this.getElements(by).size();
    }

    public void clickElement(By by) {
        this.performClickAction(this.getElement(by));
    }

    private void performClickAction(WebElement element) {
        if (this.elementAwaitCondition != null) {
            String xPath = this.getElementXPath(element);
            this.waitForConditionToPass(By.xpath(xPath), this.elementAwaitCondition);
        }

        if (this.useDefaultClick) {
            element.click();
        }else {
            Actions actions = new Actions(driver);
            actions.moveToElement(element, this.clickX, this.clickY).click().perform();
        }
        this.performedAction();
    }

    public boolean isElementPresent(By by) {
        return this.getElement(by) != null;
    }

    public void clickElementImmediately(By by) {
        this.getElement(by).click();
    }
    
    public SeleniumWrapper setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
    
    public SeleniumWrapper setAttempts(int attempts) {
        this.attempts = attempts;
        return this;
    }

    public SeleniumWrapper setClickParameterLocation(int clickX, int clickY) {
        this.clickX = clickX;
        this.clickY = clickY;
        this.useDefaultClick = false;
        return this;
    }

    public SeleniumWrapper setClickParameterCondition(ElementAwaitCondition condition) {
        this.elementAwaitCondition = condition;
        return this;
    }

    private void performedAction() {
        // reset values to default state
        this.timeout = DEFAULT_TIMEOUT;
        this.attempts = DEFAULT_ATTEMPTS;
        this.useDefaultClick = DEFAULT_USE_DEFAULT_CLICK;
        this.elementAwaitCondition = null;
    }

    private String getElementXPath(WebElement element) {
        String tagName = element.getTagName();

        int index = getElementIndex(element);

        return "//" + tagName + "[" + index + "]";
    }

    private int getElementIndex(WebElement element) {
        WebElement parent = element.findElement(By.xpath(".."));

        List<WebElement> siblings = parent.findElements(By.xpath("./" + element.getTagName()));

        int index = 1;
        for (WebElement sibling : siblings) {
            if (sibling.equals(element)) {
                break;
            }
            index++;
        }
        return index;
    }
}
