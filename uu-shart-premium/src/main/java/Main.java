import knowledge.KnowledgeRepository;
import selenium.SeleniumWrapper;
import sites.unicorn.UnicornSiteWrapper;
import sites.unicorn.solvers.*;

public class Main {
    public static void main(String[] args) {

        /*
            TODO LIST:

            [a] 1. Check answers
                [ ] T1
                [ ] T2
                [ ] T3
                [ ] T4
                [ ] T6
                [ ] T7
                [ ] T8
                [ ] T9
                [ ] T10
                [ ] T11
            [ ] 2. Retry tests if answers were gathered
            [ ] 3. Save to file
            [ ] 4. Input url + output name
            [ ] 5. Clean up main?
            [ ] 6. Screenshot elements?


         */




        // TODO : clean this up
        // temp main
        // algo test menu
        String tempUrl = "https://uuapp.plus4u.net/uu-coursekit-courseg01/286a85d928da49ebb60816c715ae15dc/course/testMenu";

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
        unicornSite.gatherAnswers();

        System.out.println(knowledge);
    }
}