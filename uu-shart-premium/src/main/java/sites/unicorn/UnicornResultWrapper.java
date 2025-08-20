package sites.unicorn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.ByUtils;
import selenium.SeleniumWrapper;

import java.util.List;
import java.util.Objects;

public class UnicornResultWrapper {

    private final String title;
    private final boolean correct;
    private final WebElement element;

    public UnicornResultWrapper(WebElement resultElement) {
        this.element = resultElement;
        this.correct = Objects.requireNonNull(resultElement.getAttribute("id")).startsWith("Correct");
        this.title = SeleniumWrapper.acquireText(resultElement.findElement(By.className(UnicornConstants.RESULTS_PAGE.TITLE_CLASS)));
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


    public List<WebElement> findAnswers(String correctClass, String resultClass) {
        return this.element.findElements(ByUtils.classNameParentsOnly(this.correct ? correctClass : resultClass));
    }

    public List<WebElement> findAnswersLame(String correctClass, String resultClass) {
        return this.element.findElements(ByUtils.classNameWithText(this.correct ? correctClass : resultClass));
    }
}
