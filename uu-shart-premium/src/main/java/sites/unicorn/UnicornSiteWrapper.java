package sites.unicorn;

import knowledge.KnowledgeRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.ByUtils;
import selenium.SeleniumWrapper;
import sites.UnicornTaskSolver;

import java.util.ArrayList;
import java.util.List;

public class UnicornSiteWrapper {

    private final SeleniumWrapper selenium;
    private final String testUrl;
    private final KnowledgeRepository knowledgeRepository;
    private final ArrayList<UnicornTaskSolver> solvers;

    public UnicornSiteWrapper(String testUrl, SeleniumWrapper wrapper, KnowledgeRepository knowledge) {
        this.testUrl = testUrl;
        this.selenium = wrapper;
        this.knowledgeRepository = knowledge;
        this.solvers = new ArrayList<>();
        selenium.goToUrl(testUrl);
    }


    public void login() {
        // get login button
        selenium.waitForElement(By.xpath(UnicornConstants.GLOBAL.LOGIN_XPATH));
        selenium.clickElement(By.xpath(UnicornConstants.GLOBAL.LOGIN_XPATH));
        selenium.awaitUserAction("Please login");
    }

    public void gatherAnswers() {
        selenium.waitForElement(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_CLASS));

        int testCount = selenium.getElements(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_CLASS)).size();


        for (int testIndex = 0; testIndex < testCount; testIndex++) {
            // navigate to test
            List<WebElement> tests = selenium.getElements(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_CLASS));
            tests.get(testIndex).findElement(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_START_BUTTON_CLASS)).click();

            // get title
            selenium.waitForElement(ByUtils.compoundClass(UnicornConstants.TEST_START_PAGE.TEST_TITLE));
            String testTitle = selenium.getElement(ByUtils.compoundClass(UnicornConstants.TEST_START_PAGE.TEST_TITLE)).getText();
            knowledgeRepository.startLoggingSection(testTitle);

            // start test
            selenium.clickElement(ByUtils.compoundClass(UnicornConstants.TEST_START_PAGE.TEST_START_BUTTON));

            solveTest();
        }
    }

    public void solveTest() {
        // wait for page load
        selenium.awaitElement(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.BUTTON_CHECK_CLASS));


        for (UnicornTaskSolver solver : solvers) {
            if (!solver.canSolve(selenium)) {
                continue;
            }

            solver.passProblem(selenium);
            break;
        }

        selenium.clickElement(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.CONTINUE_BUTTON_CLASS));




        // TODO : this
    }

    public void addSolver(UnicornTaskSolver solver) {
        this.solvers.add(solver);
    }
}
