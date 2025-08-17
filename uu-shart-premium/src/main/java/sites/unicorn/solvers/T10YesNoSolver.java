package sites.unicorn.solvers;

import knowledge.units.KnowledgeSingleUnit;
import knowledge.units.KnowledgeUnit;
import org.openqa.selenium.By;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;
import sites.unicorn.UnicornResultWrapper;

public class T10YesNoSolver implements UnicornTaskSolver {
    private static final String BUTTON_CLASS = "uu-coursekit-question-t10-white-frame-answer-button";

    @Override
    public String getName() {
        return "T10";
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

        //return new KnowledgeSingleUnit(result.getTitle(), this.getName(), result.findAnswers("", "").getFirst().getText());

    }


}
