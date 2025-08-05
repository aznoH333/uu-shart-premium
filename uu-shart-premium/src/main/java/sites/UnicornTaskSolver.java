package sites;

import selenium.SeleniumWrapper;

public interface UnicornTaskSolver {
    String getName();

    boolean canSolve(SeleniumWrapper selenium);

    void passProblem(SeleniumWrapper selenium);

    void storeSolution(SeleniumWrapper selenium);
}
