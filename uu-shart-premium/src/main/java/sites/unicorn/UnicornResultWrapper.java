package sites.unicorn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class UnicornResultWrapper {

    private final String title;
    private final boolean correct;
    private final WebElement element;
    public UnicornResultWrapper(WebElement resultElement) {
        this.element = resultElement;
        this.correct = Objects.requireNonNull(resultElement.getAttribute("id")).startsWith("Correct");
        this.title = resultElement.findElement(By.className(UnicornConstants.RESULTS_PAGE.TITLE_CLASS)).getText();
    }

    public String getTitle() {
        return title;
    }

    public boolean isCorrect() {
        return correct;
    }

    public WebElement getElement() {
        return element;
    }
}
