package sites.unicorn.solvers;

import org.openqa.selenium.WebElement;
import selenium.ByUtils;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;

import java.util.List;

public class T6JoinQuestionSolver implements UnicornTaskSolver {
    private final static String BUTTON_CLASS = "uu5-common-div uu-coursekit-question-t06-white-frame-answer-button";

    @Override
    public String getName() {
        return "T6Question";
    }

    @Override
    public boolean canSolve(SeleniumWrapper selenium) {
        return selenium.isElementPresent(ByUtils.compoundClass(BUTTON_CLASS));
    }

    @Override
    public void passProblem(SeleniumWrapper selenium) {
        List<WebElement> buttons = selenium.getElements(ByUtils.compoundClass(BUTTON_CLASS));


        int combinationCount = buttons.size() / 4;

        for (int i = combinationCount; i > 0; i--) {
            buttons.get(i * 4 - 1).click();
            buttons.get(i * 2 - 1).click();
        }
    }

    @Override
    public void storeSolution(SeleniumWrapper selenium) {

    }

}
