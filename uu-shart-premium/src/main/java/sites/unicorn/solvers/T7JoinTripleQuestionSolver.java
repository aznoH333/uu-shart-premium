package sites.unicorn.solvers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.ByUtils;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;

import java.util.List;

public class T7JoinTripleQuestionSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t07-white-frame-answer-button";

    @Override
    public String getName() {
        return "T7Question";
    }

    @Override
    public boolean canSolve(SeleniumWrapper selenium) {
        return selenium.isElementPresent(By.className(BUTTON_CLASS));
    }

    @Override
    public void passProblem(SeleniumWrapper selenium) {
        List<WebElement> buttons = selenium.getElements(ByUtils.compoundClass(BUTTON_CLASS));


        int combinationCount = buttons.size() / 2 / 3;

        for (int i = combinationCount; i > 0; i--) {
            buttons.get(i * 6 - 1).click();
            buttons.get(i * 4 - 1).click();
            buttons.get(i * 2 - 1).click();
        }
    }

    @Override
    public void storeSolution(SeleniumWrapper selenium) {

    }
}
