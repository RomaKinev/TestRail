package ui.steps;

import lombok.extern.log4j.Log4j2;
import ui.dto.*;
import ui.pages.*;


@Log4j2
public class TestSuiteStep {

    ProjectsPage projectsPage;
    TestSuitesPage testSuitesPage;

    public TestSuiteStep(ProjectsPage projectsPage, TestSuitesPage testSuitesPage) {
        this.projectsPage = projectsPage;
        this.testSuitesPage = testSuitesPage;
    }

    public void createSuite(Project project, Suite suite) {
        log.info("Create suite '{}' in project '{}'", suite.getName(), project.getName());
        projectsPage.isPageOpen().createProjectWithSuites(project);
        projectsPage.open()
                .isPageOpen()
                .openProject(project.getName())
                .goToTestSuites()
                .openAddSuiteForm()
                .createSuite(suite)
                .isSuiteCreated();
    }

    public void checkCasesCount(Suite suite, int expectedCount) {
        log.info("Verify suite '{}' shows {} case(s)", suite.getName(), expectedCount);
        projectsPage.goToTestSuites()
                .hasCasesCount(suite.getName(), expectedCount);
    }

    public void addCaseToSuite(TestCase testCase) {
        log.info("Add test case '{}' to the suite", testCase.getTitle());
        testSuitesPage.addTestCaseToSuite(testCase)
                .isCaseCreated();
    }

    public void deleteSuite(Suite suite) {
        log.info("Delete suite '{}'", suite.getName());
        projectsPage.goToTestSuites()
                .openSuiteEdit(suite.getName())
                .deleteSuite()
                .isSuiteDeleted(suite.getName());
    }
}
