import knowledge.KnowledgeRepository;
import selenium.SeleniumWrapper;
import sites.unicorn.UnicornSiteWrapper;
import sites.unicorn.solvers.*;

public class Main {
    public static void main(String[] args) {

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
        unicornSite.addSolver(new T8OrderOptionsAltSolver());
        unicornSite.addSolver(new T9OrderOptionsSolver());


        unicornSite.login();
        unicornSite.gatherAnswers();

        System.out.println("Hello, World!");
    }
}