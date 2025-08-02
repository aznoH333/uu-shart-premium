package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;

public class SeleniumWrapper {

    
    private int timeout;
    private int attempts;
    private final WebDriver driver;
    private final Scanner scanner;

    public SeleniumWrapper() {
        this.driver = new FirefoxDriver();
        this.scanner = new Scanner(System.in);
        this.timeout = 60;
        this.attempts = 10;
    }

    public SeleniumWrapper goToUrl(String url) {
        this.driver.get(url);
        return this;
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

    public void clickElement(By by) {
        this.awaitElement(by).click();
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

}
