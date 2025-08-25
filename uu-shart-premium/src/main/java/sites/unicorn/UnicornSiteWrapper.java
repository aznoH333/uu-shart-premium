package sites.unicorn;

import knowledge.KnowledgeRepository;
import knowledge.units.KnowledgeUnit;
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
            boolean repeatTest;

            do {
                // navigate to test
                List<WebElement> tests = selenium.getElements(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_CLASS));
                tests.get(testIndex).findElement(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_START_BUTTON_CLASS)).click();

                // get title
                selenium.waitForElement(ByUtils.compoundClass(UnicornConstants.TEST_START_PAGE.TEST_TITLE));
                String testTitle = selenium.getElement(ByUtils.compoundClass(UnicornConstants.TEST_START_PAGE.TEST_TITLE)).getText();
                knowledgeRepository.startLoggingSection(testTitle);

                // start test
                selenium.clickElement(ByUtils.compoundClass(UnicornConstants.TEST_START_PAGE.TEST_START_BUTTON));

                repeatTest = solveTest(); // solve test returns a boolean if new knowledge was found
                // TODO : wrap repeat in some utility class
                this.selenium.goToUrl(testUrl); // return to tests
                this.selenium.waitForElement(ByUtils.compoundClass(UnicornConstants.TEST_MENU_PAGE.TEST_CARD_CLASS));
            } while (repeatTest);
        }
    }

    /**
     * Solves test
     * @return a boolean indicating if new knowledge was found
     */
    public boolean solveTest() {
        // wait for page load
        selenium.awaitElement(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.BUTTON_CHECK_CLASS));
        ArrayList<UnicornTaskSolver> solverStack = new ArrayList<>();
        // solve tasks
        while (!isTestComplete()) {
            boolean solved = false;
            for (UnicornTaskSolver solver : solvers) {
                if (!solver.canSolve(selenium)) {
                    continue;
                }
                solver.passProblem(selenium);
                solved = true;
                solverStack.add(solver);
                selenium.clickElementInCollection(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.CONTINUE_BUTTON_CLASS), 1);
                break;
            }

            if (!solved) {
                System.out.println("No solution found"); // TODO : proper error handling
            }
        }
        // complete test
        selenium.clickElement(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.FINISH_TEST_BUTTON_CLASS));
        selenium.wait(300);
        selenium.clickElementInCollection(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.CONFIRM_TEST_BUTTON_CLASS), 1);
        selenium.wait(300); // Wait for animations to finish
        selenium.waitForElement(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.GO_TO_RESULTS_BUTTON_CLASS));
        selenium.clickFirstInCollection(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.GO_TO_RESULTS_BUTTON_CLASS));

        // read results
        selenium.waitForElement(By.className(UnicornConstants.RESULTS_PAGE.RESULTS_CONTAINER_CLASS));
        List<WebElement> results = selenium.getElements(By.className(UnicornConstants.RESULTS_PAGE.RESULTS_CONTAINER_CLASS));

        boolean foundNew = false;
        int counter = 0;
        for (WebElement result : results) {
            UnicornTaskSolver solver = solverStack.removeFirst();
            UnicornResultWrapper resultWrapper = new UnicornResultWrapper(result);
            counter++;
            System.out.println(resultWrapper.getTitle() + " " + counter);

            try { // TODO : solution off by one?
                KnowledgeUnit knowledge = solver.generateSolution(resultWrapper);
                if (!knowledgeRepository.containsKnowledge(knowledge.getTitle())) {
                    knowledgeRepository.logKnowledge(knowledge);
                    foundNew = true;
                }
            } catch (Exception e) {
                throw e;
            }
        }

        return foundNew;
    }

    public void addSolver(UnicornTaskSolver solver) {
        this.solvers.add(solver);
    }

    private boolean isTestComplete() {
        return selenium.isElementPresent(ByUtils.compoundClass(UnicornConstants.TEST_PAGE.FINISH_TEST_BUTTON_CLASS));
    }
}
