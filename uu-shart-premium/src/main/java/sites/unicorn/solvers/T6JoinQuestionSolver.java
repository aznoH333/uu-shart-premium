package sites.unicorn.solvers;

import knowledge.units.KnowledgeGroupUnit;
import knowledge.units.KnowledgeSingleUnit;
import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.WebElement;
import selenium.ByUtils;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornElementConditions;
import sites.unicorn.UnicornResultWrapper;

import java.util.ArrayList;
import java.util.List;

public class T6JoinQuestionSolver implements UnicornTaskSolver {
    private final static String BUTTON_CLASS = "uu-coursekit-question-t06-white-frame-answer-button";
    private static final String CORRECT_CLASS = "uu-coursekit-correct-state";
    private static final String RESULT_CLASS = "uu-coursekit-result-state";
    @Override
    public String getName() {
        return "T6";
    }

    @Override
    public boolean canSolve(SeleniumWrapper selenium) {
        return selenium.isElementPresent(ByUtils.compoundClass(BUTTON_CLASS));
    }

    @Override
    public void passProblem(SeleniumWrapper selenium) {
        List<WebElement> buttons = selenium.getElements(ByUtils.classNameParentsOnly(BUTTON_CLASS));
        int combinationCount = buttons.size() / 2;
        int deadIndicies = 0;

        for (int i = 0; i < combinationCount; i++) {
            // click top left due to some jpeg fuckery
            selenium.setClickParameterCondition(UnicornElementConditions.HAS_TEXT_OR_IMAGE).setClickParameterLocation(0, 0).clickElementInCollection(ByUtils.classNameParentsOnly(BUTTON_CLASS), deadIndicies);
            selenium.setClickParameterCondition(UnicornElementConditions.HAS_TEXT_OR_IMAGE).setClickParameterLocation(0, 0).clickElementInCollection(ByUtils.classNameParentsOnly(BUTTON_CLASS), deadIndicies + (combinationCount - i));
            deadIndicies += 2;
        }
    }

    @Override
    public KnowledgeUnit generateSolution(UnicornResultWrapper result) {
        List<WebElement> answers = result.findAnswersLame(CORRECT_CLASS, RESULT_CLASS);

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int i = 0; i < answers.size(); i += 2) {


            ArrayList<String> row = new ArrayList<>();
            row.add(answers.get(i).getText());
            row.add(answers.get(i + 1).getText());
            results.add(row);
        }

        return new KnowledgeGroupUnit(result.getTitle(), this.getName(), results);
    }

}
