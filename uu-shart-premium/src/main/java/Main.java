import knowledge.KnowledgeRepository;
import selenium.SeleniumWrapper;
import selenium.UnicornSiteWrapper;

public class Main {
    public static void main(String[] args) {


        // temp main
        // algo test menu
        String tempUrl = "https://uuapp.plus4u.net/uu-coursekit-courseg01/286a85d928da49ebb60816c715ae15dc/course/testMenu";

        KnowledgeRepository knowledge = new KnowledgeRepository(); // TODO load from file?
        SeleniumWrapper selenium = new SeleniumWrapper();
        UnicornSiteWrapper unicornSite = new UnicornSiteWrapper(tempUrl, selenium, knowledge);

        unicornSite.login();
        unicornSite.gatherAnswers();

        System.out.println("Hello, World!");
    }
}