package tests.ui;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static dto.ProjectFactory.getProject;
import static dto.RunFactory.getRun;
import static dto.SuiteFactory.getSuite;
import static dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class ReportTest extends BaseUITest {

    @Test(description = "View test run statistics", groups = {"ui"})
    public void viewRunStatisticsTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.viewRunStatistics(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Test(description = "Export run results to a file", groups = {"ui"})
    public void exportRunResultsTest() throws FileNotFoundException {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.exportRunResults(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Test(description = "Compare results of two runs", groups = {"ui"})
    public void compareRunsTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.compareRuns(getProject(), getSuite(), getTestCase(), getRun(), getRun());
    }
}
