package sites.unicorn.solvers;

import org.openqa.selenium.By;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;

public class T10YesNoSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t10-white-frame-answer-button";

    @Override
    public String getName() {
        return "T10Question";
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
    public void storeSolution(SeleniumWrapper selenium) {

    }
}
