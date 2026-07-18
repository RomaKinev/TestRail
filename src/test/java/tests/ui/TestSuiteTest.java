package tests.ui;

import ui.dto.Suite;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.ProjectFactory.getProject;
import static ui.dto.SuiteFactory.getSuite;
import static ui.dto.TestCaseFactory.getTestCase;


@Test(testName = "Test suites functionality tests")
public class TestSuiteTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create test suite")
    @Test(description = "Create test suite", groups = {"ui", "smoke"})
    public void createSuiteTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testSuiteStep.createSuite(getProject(), getSuite());
    }

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.NORMAL)
    @Description("Suite shows test cases count")
    @Test(description = "Suite shows test cases count", groups = {"ui"})
    public void suiteShowsCasesCountTest() {
        Suite suite = getSuite();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testSuiteStep.createSuite(getProject(), suite);
        testSuiteStep.checkCasesCount(suite, 0);
    }

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add test case to suite")
    @Test(description = "Add test case to suite", groups = {"ui"})
    public void addCaseToSuiteTest() {
        Suite suite = getSuite();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testSuiteStep.createSuite(getProject(), suite);
        testSuiteStep.addCaseToSuite(getTestCase());
        testSuiteStep.checkCasesCount(suite, 1);
    }

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete test suite")
    @Test(description = "Delete test suite", groups = {"ui"})
    public void deleteSuiteTest() {
        Suite suite = getSuite();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testSuiteStep.createSuite(getProject(), suite);
        testSuiteStep.deleteSuite(suite);
    }
}
