package steps;

import dto.Project;
import dto.TestRun;
import dto.Suite;
import dto.TestCase;
import lombok.extern.log4j.Log4j2;
import pages.ProjectsPage;
import pages.ReportsPage;
import pages.TestRunPage;
import pages.TestSuitesPage;

import java.io.File;
import java.io.FileNotFoundException;

@Log4j2
public class TestRunStep {

    ProjectsPage projectsPage;
    TestSuitesPage testSuitesPage;
    TestRunPage testRunPage;
    ReportsPage reportsPage;

    public TestRunStep(ProjectsPage projectsPage, TestSuitesPage testSuitesPage,
                   TestRunPage testRunPage, ReportsPage reportsPage) {
        this.projectsPage = projectsPage;
        this.testSuitesPage = testSuitesPage;
        this.testRunPage = testRunPage;
        this.reportsPage = reportsPage;
    }

    private void createProjectWithRun(Project project, Suite suite, TestCase testCase, TestRun run) {
        log.info("Prepare project '{}' with a suite, a case and run '{}'", project.getName(), run.getName());
        projectsPage.isPageOpen().createProjectWithSuites(project);
        projectsPage.open()
                .isPageOpen()
                .openProject(project.getName())
                .goToTestSuites()
                .openAddSuiteForm()
                .createSuite(suite)
                .isSuiteCreated();
        testSuitesPage.addTestCaseToSuite(testCase)
                .isCaseCreated();
        projectsPage.open()
                .openProject(project.getName())
                .goToTestRuns()
                .openAddRunForm()
                .createRun(run)
                .isRunCreated();
    }

    public void createRun(Project project, Suite suite, TestCase testCase, TestRun run) {
        createProjectWithRun(project, suite, testCase, run);
    }

    public void addResult(Project project, Suite suite, TestCase testCase, TestRun run,
                          String status, String comment) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.openFirstTest()
                .addResult(status, comment)
                .isResultAdded(status);
    }

    public void addResultWithAttachment(Project project, Suite suite, TestCase testCase, TestRun run,
                                        String status, String comment, File file) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.openFirstTest()
                .addResultWithAttachment(status, comment, file)
                .isResultAdded(status);
    }

    public void exportRunResults(Project project, Suite suite, TestCase testCase, TestRun run)
            throws FileNotFoundException {
        createProjectWithRun(project, suite, testCase, run);
        File file = testRunPage.exportResultsToExcel();
        if (file == null || !file.exists() || file.length() == 0) {
            throw new AssertionError("Exported file was not downloaded or is empty: " + file);
        }
        log.info("Exported file: {} ({} bytes)", file.getName(), file.length());
    }

    public void viewRunStatistics(Project project, Suite suite, TestCase testCase, TestRun run) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.openFirstTest()
                .addResult("Passed", "Result added by autotest")
                .isResultAdded("Passed");
        testRunPage.backToRun()
                .hasStatistic("Passed", 1);
    }

    public void filterRunByStatus(Project project, Suite suite, TestCase testCase, TestRun run) {
        createProjectWithRun(project, suite, testCase, run);
        // a fresh test is Untested: the Failed filter hides it, Untested shows it
        testRunPage.filterByStatus("Failed")
                .isTestHidden(testCase.getTitle());
        testRunPage.filterByStatus("Untested")
                .isTestVisible(testCase.getTitle());
    }

    public void closeRun(Project project, Suite suite, TestCase testCase, TestRun run) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.closeRun()
                .isRunClosed();
    }

    public void compareRuns(Project project, Suite suite, TestCase testCase, TestRun run1, TestRun run2) {
        createProjectWithRun(project, suite, testCase, run1);
        // second run in the same project
        projectsPage.goToTestRuns()
                .openAddRunForm()
                .createRun(run2)
                .isRunCreated();
        reportsPage.openComparisonForCasesReport()
                .selectSuiteAndAllRuns(suite.getName())
                .generateReport()
                .isReportCreated();
    }
}
