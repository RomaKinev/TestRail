package tests.ui;

import org.testng.annotations.Test;

import java.io.File;

import static dto.ProjectFactory.getProject;
import static dto.RunFactory.getRun;
import static dto.SuiteFactory.getSuite;
import static dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class TestRunTest extends BaseUITest {

    @Test(description = "Create test run", groups = {"ui", "smoke"})
    public void createRunTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.createRun(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Test(description = "Add passed result to a test", groups = {"ui"})
    public void addPassedResultTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.addResult(getProject(), getSuite(), getTestCase(), getRun(),
                "Passed", "Result added by autotest");
    }

    @Test(description = "Add failed result with attachment", groups = {"ui"})
    public void addFailedResultWithAttachmentTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.addResultWithAttachment(getProject(), getSuite(), getTestCase(), getRun(),
                "Failed", "Test failed, see attached screenshot",
                new File("src/test/resources/attachment.png"));
    }

    @Test(description = "Filter tests in run by status", groups = {"ui"})
    public void filterByStatusTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.filterRunByStatus(getProject(), getSuite(), getTestCase(), getRun());
    }

    @Test(description = "Close test run", groups = {"ui"})
    public void closeRunTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        runStep.closeRun(getProject(), getSuite(), getTestCase(), getRun());
    }
}
