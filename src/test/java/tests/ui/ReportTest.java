package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static dto.ProjectFactory.getProject;
import static dto.RunFactory.getRun;
import static dto.SuiteFactory.getSuite;
import static dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class ReportTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Reports")
    @Severity(SeverityLevel.NORMAL)
    @Description("View test run statistics")
    @Test(description = "View test run statistics", groups = {"ui"})
    public void viewRunStatisticsTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.viewRunStatistics(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Owner("Roma")
    @Feature("Reports")
    @Severity(SeverityLevel.NORMAL)
    @Description("Export run results to a file")
    @Test(description = "Export run results to a file", groups = {"ui"})
    public void exportRunResultsTest() throws FileNotFoundException {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.exportRunResults(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Owner("Roma")
    @Feature("Reports")
    @Severity(SeverityLevel.NORMAL)
    @Description("Compare results of two runs")
    @Test(description = "Compare results of two runs", groups = {"ui"})
    public void compareRunsTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.compareRuns(getProject(), getSuite(), getTestCase(), getRun(), getRun());
    }
}
