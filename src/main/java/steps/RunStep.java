package steps;

import dto.Project;
import dto.Run;
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
public class RunStep {

    ProjectsPage projectsPage;
    TestSuitesPage testSuitesPage;
    TestRunPage testRunPage;
    ReportsPage reportsPage;

    public RunStep(ProjectsPage projectsPage, TestSuitesPage testSuitesPage,
                   TestRunPage testRunPage, ReportsPage reportsPage) {
        this.projectsPage = projectsPage;
        this.testSuitesPage = testSuitesPage;
        this.testRunPage = testRunPage;
        this.reportsPage = reportsPage;
    }

    private void createProjectWithRun(Project project, Suite suite, TestCase testCase, Run run) {
        log.info("Готовим проект '{}' со сьютом, кейсом и раном '{}'", project.getName(), run.getName());
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

    public void createRun(Project project, Suite suite, TestCase testCase, Run run) {
        createProjectWithRun(project, suite, testCase, run);
    }

    public void addResult(Project project, Suite suite, TestCase testCase, Run run,
                          String status, String comment) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.openFirstTest()
                .addResult(status, comment)
                .isResultAdded(status);
    }

    public void addResultWithAttachment(Project project, Suite suite, TestCase testCase, Run run,
                                        String status, String comment, File file) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.openFirstTest()
                .addResultWithAttachment(status, comment, file)
                .isResultAdded(status);
    }

    public void exportRunResults(Project project, Suite suite, TestCase testCase, Run run)
            throws FileNotFoundException {
        createProjectWithRun(project, suite, testCase, run);
        File file = testRunPage.exportResultsToExcel();
        if (file == null || !file.exists() || file.length() == 0) {
            throw new AssertionError("Экспортированный файл не скачан или пуст: " + file);
        }
        log.info("Экспортированный файл: {} ({} байт)", file.getName(), file.length());
    }

    public void viewRunStatistics(Project project, Suite suite, TestCase testCase, Run run) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.openFirstTest()
                .addResult("Passed", "Result added by autotest")
                .isResultAdded("Passed");
        testRunPage.backToRun()
                .hasStatistic("Passed", 1);
    }

    public void filterRunByStatus(Project project, Suite suite, TestCase testCase, Run run) {
        createProjectWithRun(project, suite, testCase, run);
        // свежий тест имеет статус Untested: фильтр Failed скрывает его, Untested — показывает
        testRunPage.filterByStatus("Failed")
                .isTestHidden(testCase.getTitle());
        testRunPage.filterByStatus("Untested")
                .isTestVisible(testCase.getTitle());
    }

    public void closeRun(Project project, Suite suite, TestCase testCase, Run run) {
        createProjectWithRun(project, suite, testCase, run);
        testRunPage.closeRun()
                .isRunClosed();
    }

    public void compareRuns(Project project, Suite suite, TestCase testCase, Run run1, Run run2) {
        createProjectWithRun(project, suite, testCase, run1);
        // второй ран в том же проекте
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
