package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.io.File;

import static ui.dto.ProjectFactory.getProject;
import static ui.dto.TestRunFactory.getRun;
import static ui.dto.SuiteFactory.getSuite;
import static ui.dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class TestRunTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Test Runs")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create test run")
    @Test(description = "Create test run", groups = {"ui", "smoke"})
    public void createRunTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.createRun(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Owner("Roma")
    @Feature("Test Runs")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add passed result to a test")
    @Test(description = "Add passed result to a test", groups = {"ui"})
    public void addPassedResultTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.addResult(getProject(), getSuite(), getTestCase(), getRun(),
                "Passed", "Result added by autotest");
    }

    @Owner("Roma")
    @Feature("Test Runs")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add failed result with attachment")
    @Test(description = "Add failed result with attachment", groups = {"ui"})
    public void addFailedResultWithAttachmentTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.addResultWithAttachment(getProject(), getSuite(), getTestCase(), getRun(),
                "Failed", "Test failed, see attached screenshot",
                new File("src/test/resources/attachment.png"));
    }

    @Owner("Roma")
    @Feature("Test Runs")
    @Severity(SeverityLevel.NORMAL)
    @Description("Filter tests in run by status")
    @Test(description = "Filter tests in run by status", groups = {"ui"})
    public void filterByStatusTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.filterRunByStatus(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Owner("Roma")
    @Feature("Test Runs")
    @Severity(SeverityLevel.NORMAL)
    @Description("Close test run")
    @Test(description = "Close test run", groups = {"ui"})
    public void closeRunTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testRunStep.closeRun(getProject(), getSuite(), getTestCase(), getRun());
    }
}
