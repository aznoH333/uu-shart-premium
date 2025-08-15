package sites.unicorn.solvers;

import knowledge.units.KnowledgeCollectionUnit;
import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;

public class T8OrderOptionsAltSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t08-answer-option";

    @Override
    public String getName() {
        return "T8";
    }

    @Override
    public boolean canSolve(SeleniumWrapper selenium) {
        return selenium.isElementPresent(By.className(BUTTON_CLASS));
    }

    @Override
    public void passProblem(SeleniumWrapper selenium) {
        int count = selenium.countElements(By.className(BUTTON_CLASS));

        for (int i = 0; i < count; i++) {
            selenium.clickLastInCollection(By.className(BUTTON_CLASS));
        }
    }

    @Override
    public KnowledgeUnit generateSolution(UnicornResultWrapper result) {
        return new KnowledgeCollectionUnit(result.getTitle(), this.getName(), result.getAnswerElements().stream().map(WebElement::getText).toList());
    }

}
