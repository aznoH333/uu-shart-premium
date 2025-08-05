package sites.unicorn.solvers;

import org.openqa.selenium.By;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;

public class T9OrderOptionsSolver implements UnicornTaskSolver {
    private final static String BUTTON_CLASS = "uu-coursekit-question-t09-answer-option";

    @Override
    public String getName() {
        return "T9Question";
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
    public void storeSolution(SeleniumWrapper selenium) {

    }
}
