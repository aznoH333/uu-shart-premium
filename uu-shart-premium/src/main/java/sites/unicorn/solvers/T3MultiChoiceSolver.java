package sites.unicorn.solvers;

import knowledge.units.KnowledgeCollectionUnit;
import knowledge.units.KnowledgeSingleUnit;
import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;



public class T3MultiChoiceSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t03-white-frame-answer-button";

    @Override
    public String getName() {
        return "T3";
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
        return new KnowledgeSingleUnit("", "", "");

        //return new KnowledgeCollectionUnit(result.getTitle(), this.getName(), result.findAnswers("", "").stream().map(WebElement::getText).toList());
    }

}
