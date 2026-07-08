package tests.ui;

import dto.Project;
import dto.Suite;
import dto.TestCase;
import org.testng.annotations.Test;

import static dto.ProjectFactory.getProject;
import static dto.SuiteFactory.getSuite;
import static dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginUITest.CONFIG;

public class TestSuiteUITest extends BaseUITest {

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
