package sites.unicorn.solvers;

import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;

public class T11SingleChoiceMultiPartSolver implements UnicornTaskSolver {
    private final static String BUTTON_CLASS = "uu-coursekit-question-t11-white-frame-answer-button";
    private final static String REPLACEMENT_BUTTON = "uu-coursekit-question-t11-task-replacement-button";

    @Override
    public String getName() {
        return "T11Question";
    }

    @Override
    public boolean canSolve(SeleniumWrapper selenium) {
        return selenium.isElementPresent(By.className(BUTTON_CLASS));
    }

    @Override
    public void passProblem(SeleniumWrapper selenium) {
        int partCount = selenium.countElements(By.className(REPLACEMENT_BUTTON));

        for (int i = 0; i < partCount; i++) {
            selenium.clickElementInCollection(By.className(REPLACEMENT_BUTTON), i);
            selenium.clickFirstInCollection(By.className(BUTTON_CLASS));
        }
    }

    @Override
    public KnowledgeUnit generateSolution(UnicornResultWrapper result) {
        return null;
    }


}
