package sites.unicorn.solvers;

import knowledge.units.KnowledgeSingleUnit;
import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;

public class T1PickOneSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t01-white-frame-answer-button";
    private static final String CORRECT_CLASS = "uu-coursekit-correct-state";
    private static final String RESULT_CLASS = "uu-coursekit-result-state";

    @Override
    public String getName() {
        return "T1";
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
        return new KnowledgeSingleUnit(result.getTitle(), this.getName(), SeleniumWrapper.acquireText(result.findAnswers(CORRECT_CLASS, RESULT_CLASS).getFirst()));
    }

}
