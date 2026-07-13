package tests.ui;

import dto.Project;
import dto.Suite;
import dto.TestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static dto.ProjectFactory.getProject;
import static dto.SuiteFactory.getSuite;
import static dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class TestSuiteTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create test suite")
    @Test(description = "Create test suite", groups = {"ui", "smoke"})
    public void createSuiteTest() {
        Project project = getProject();
        Suite suite = getSuite();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen().createProjectWithSuites(project);
        projectsPage.open()
                .isPageOpen()
                .openProject(project.getName())
                .goToTestSuites()
                .openAddSuiteForm()
                .createSuite(suite)
                .isSuiteCreated();
    }

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.NORMAL)
    @Description("Suite shows test cases count")
    @Test(description = "Suite shows test cases count", groups = {"ui"})
    public void suiteShowsCasesCountTest() {
        Project project = getProject();
        Suite suite = getSuite();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen().createProjectWithSuites(project);
        projectsPage.open()
                .openProject(project.getName())
                .goToTestSuites()
                .openAddSuiteForm()
                .createSuite(suite)
                .isSuiteCreated();
        projectsPage.goToTestSuites()
                .hasCasesCount(suite.getName(), 0);
    }

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add test case to suite")
    @Test(description = "Add test case to suite", groups = {"ui"})
    public void addCaseToSuiteTest() {
        Project project = getProject();
        Suite suite = getSuite();
        TestCase testCase = getTestCase();

        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen().createProjectWithSuites(project);
        projectsPage.open()
                .openProject(project.getName())
                .goToTestSuites()
                .openAddSuiteForm()
                .createSuite(suite)
                .isSuiteCreated();
        testSuitesPage.addTestCaseToSuite(testCase)
                .isCaseCreated();
        projectsPage.goToTestSuites()
                .hasCasesCount(suite.getName(), 1);
    }

    @Owner("Roma")
    @Feature("Test Suites")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete test suite")
    @Test(description = "Delete test suite", groups = {"ui"})
    public void deleteSuiteTest() {
        Project project = getProject();
        Suite suite = getSuite();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen().createProjectWithSuites(project);
        projectsPage.open()
                .openProject(project.getName())
                .goToTestSuites()
                .openAddSuiteForm()
                .createSuite(suite)
                .isSuiteCreated();
        projectsPage.goToTestSuites()
                .openSuiteEdit(suite.getName())
                .deleteSuite()
                .isSuiteDeleted(suite.getName());
    }
}
