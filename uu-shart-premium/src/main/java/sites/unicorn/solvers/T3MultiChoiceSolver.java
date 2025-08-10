package sites.unicorn.solvers;

import knowledge.units.KnowledgeCollectionUnit;
import knowledge.units.KnowledgeSingleUnit;
import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;

import java.util.stream.Collectors;


public class T3MultiChoiceSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t03-white-frame-answer-button";

    @Override
    public String getName() {
        return "T3Question";
    }

    @Override
    public boolean canSolve(SeleniumWrapper selenium) {
        return selenium.isElementPresent(By.className(BUTTON_CLASS));
    }

    @Override
    public void passProblem(SeleniumWrapper selenium) {
        selenium.clickFirstInCollection(By.className(BUTTON_CLASS));

    }

    @Override
    public KnowledgeUnit generateSolution(UnicornResultWrapper result) {
        return new KnowledgeCollectionUnit(result.getTitle(), result.getAnswerElements().stream().map(WebElement::getText).toList());
    }

}
