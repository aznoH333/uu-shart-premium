package sites.unicorn.solvers;

import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.ByUtils;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornElementConditions;
import sites.unicorn.UnicornResultWrapper;

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
        List<WebElement> buttons = selenium.getElements(ByUtils.classNameParentsOnly(BUTTON_CLASS));

        int combinationCount = buttons.size() / 3;
        int deadIndicies = 0;

        for (int i = 0; i < combinationCount; i++) {
            selenium.setClickParameterCondition(UnicornElementConditions.HAS_TEXT_OR_IMAGE).setClickParameterLocation(0, 0).clickElementInCollection(ByUtils.classNameParentsOnly(BUTTON_CLASS), deadIndicies);
            selenium.setClickParameterCondition(UnicornElementConditions.HAS_TEXT_OR_IMAGE).setClickParameterLocation(0, 0).clickElementInCollection(ByUtils.classNameParentsOnly(BUTTON_CLASS), deadIndicies + (combinationCount - i));
            selenium.setClickParameterCondition(UnicornElementConditions.HAS_TEXT_OR_IMAGE).setClickParameterLocation(0, 0).clickElementInCollection(ByUtils.classNameParentsOnly(BUTTON_CLASS), deadIndicies + ((combinationCount - i) * 2));

            deadIndicies += 3;
        }
    }

    @Override
    public KnowledgeUnit generateSolution(UnicornResultWrapper result) {
        return null;
    }


}
