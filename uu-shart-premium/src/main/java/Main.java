import knowledge.KnowledgeRepository;
import selenium.SeleniumWrapper;
import sites.unicorn.UnicornSiteWrapper;
import sites.unicorn.solvers.*;
import utils.FileUtils;
import utils.Logger;

public class Main {
    public static void main(String[] args) {

        /*
            TODO LIST:

            [x] 1. Check answers
            [x] 2. Retry tests if answers were gathered
            [a] 3. Save to file
            [ ] 4. Input url + output name
            [ ] 5. Clean up main?
            [ ] 6. Screenshot elements?


         */

        String outputName = "testOutput.txt";


        // TODO : clean this up
        // temp main
        // algo test menu
        String tempUrl = "https://uuapp.plus4u.net/uu-coursekit-courseg01/99923616732453117-8d5e19993bc042da8b5dd7812c93dfef/course/testMenu";

        KnowledgeRepository knowledge = new KnowledgeRepository(); // TODO load from file?

        SeleniumWrapper selenium = new SeleniumWrapper();
        UnicornSiteWrapper unicornSite = new UnicornSiteWrapper(tempUrl, selenium, knowledge);

        // setup
        unicornSite.addSolver(new T1PickOneSolver());
        unicornSite.addSolver(new T2SingleChoiceSolver());
        unicornSite.addSolver(new T3MultiChoiceSolver());
        unicornSite.addSolver(new T4PicturePickSolver());
        unicornSite.addSolver(new T6JoinQuestionSolver());
        unicornSite.addSolver(new T7JoinTripleQuestionSolver());
        unicornSite.addSolver(new T8OrderOptionsAltSolver());
        unicornSite.addSolver(new T9OrderOptionsSolver());
        unicornSite.addSolver(new T10YesNoSolver());
        unicornSite.addSolver(new T11SingleChoiceMultiPartSolver());


        unicornSite.login();

        try {
            unicornSite.gatherAnswers();
        } catch (Exception e) {
            Logger.logMessage("Unhandled exception: " + e.getMessage());
        }
        // log results
        FileUtils.writeFile("logOutput.txt", Logger.getLog());
        FileUtils.writeFile(outputName, knowledge.toString());
    }
}