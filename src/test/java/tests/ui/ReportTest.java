package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static ui.dto.ProjectFactory.getProject;
import static ui.dto.TestRunFactory.getRun;
import static ui.dto.SuiteFactory.getSuite;
import static ui.dto.TestCaseFactory.getTestCase;


public class ReportTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Reports")
    @Severity(SeverityLevel.NORMAL)
    @Description("View test run statistics")
    @Test(description = "View test run statistics", groups = {"ui"})
    public void viewRunStatisticsTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.viewRunStatistics(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Owner("Roma")
    @Feature("Reports")
    @Severity(SeverityLevel.NORMAL)
    @Description("Export run results to a file")
    @Test(description = "Export run results to a file", groups = {"ui"})
    public void exportRunResultsTest() throws FileNotFoundException {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.exportRunResults(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Owner("Roma")
    @Feature("Reports")
    @Severity(SeverityLevel.NORMAL)
    @Description("Compare results of two runs")
    @Test(description = "Compare results of two runs", groups = {"ui"})
    public void compareRunsTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.compareRuns(getProject(), getSuite(), getTestCase(), getRun(), getRun());
    }
}