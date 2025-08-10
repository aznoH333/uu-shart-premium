package sites.unicorn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.ByUtils;

import java.util.List;
import java.util.Objects;

public class UnicornResultWrapper {

    private final String title;
    private final boolean correct;
    private final WebElement element;
    private final List<WebElement> answerElements;
    public UnicornResultWrapper(WebElement resultElement) {
        this.element = resultElement;
        this.correct = Objects.requireNonNull(resultElement.getAttribute("id")).startsWith("Correct");
        this.title = resultElement.findElement(By.className(UnicornConstants.RESULTS_PAGE.TITLE_CLASS)).getText();
        this.answerElements = resultElement
                .findElements(ByUtils.classNameParentsOnly(
                        this.correct ?
                                UnicornConstants.RESULTS_PAGE.CORRECT_ANSWER_CLASS :
                                UnicornConstants.RESULTS_PAGE.RESULT_ANSWER_CLASS));

        this.answerElements.removeFirst(); // for some reason there is a single empty element


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

    public List<WebElement> getAnswerElements() {
        return answerElements;
    }
}
