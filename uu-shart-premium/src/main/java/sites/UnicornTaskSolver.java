package sites;

import knowledge.units.KnowledgeUnit;
import selenium.SeleniumWrapper;
import sites.unicorn.UnicornResultWrapper;

public interface UnicornTaskSolver {
    String getName();

    boolean canSolve(SeleniumWrapper selenium);

    void passProblem(SeleniumWrapper selenium);

    KnowledgeUnit generateSolution(UnicornResultWrapper result);
}
