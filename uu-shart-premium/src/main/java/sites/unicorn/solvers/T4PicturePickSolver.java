package sites.unicorn.solvers;

import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;


public class T4PicturePickSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t04-white-frame-answer-border";

    @Override
    public String getName() {
        return "T4Question";
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
        return null;
    }

}
