package ui.steps;

import lombok.extern.log4j.Log4j2;
import ui.dto.TestCase;
import ui.pages.*;


@Log4j2
public class TestCaseStep {

    ProjectsPage projectsPage;
    TestCasesPage testCasesPage;
    TestCasePage testCasePage;

    public TestCaseStep(ProjectsPage projectsPage, TestCasesPage testCasesPage, TestCasePage testCasePage) {
        this.projectsPage = projectsPage;
        this.testCasesPage = testCasesPage;
        this.testCasePage = testCasePage;
    }

    private void openCases(String projectName) {
        projectsPage.isPageOpen()
                .openTestCasesByProject(projectName)
                .isPageOpen();
    }

    public void createTestCase(String projectName, TestCase testCase) {
        log.info("Create test case '{}' in project '{}'", testCase.getTitle(), projectName);
        openCases(projectName);
        testCasesPage.isPageOpen()
                .addTestCase(testCase)
                .isCaseCreated();
    }

    public void createAndDeleteTestCase(String projectName, TestCase testCase) {
        log.info("Create and delete test case '{}' in project '{}'", testCase.getTitle(), projectName);
        openCases(projectName);
        testCasesPage.isPageOpen()
                .addAndDeleteTestCase(projectName, testCase)
                .isTestCaseNotVisible(testCase.getTitle());
    }

    public void openFirstCase(String projectName) {
        log.info("Open the first test case in project '{}'", projectName);
        openCases(projectName);
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle)
                .isCaseOpen(caseTitle);
    }

    public void editFirstCaseTitle(String projectName) {
        log.info("Edit the title of the first test case in project '{}'", projectName);
        openCases(projectName);
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.editTestCase(caseTitle, caseTitle + "_edited");
    }

    public void changeFirstCasePriority(String projectName) {
        log.info("Change priority of the first test case in project '{}'", projectName);
        openCases(projectName);
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.changePriorityToDifferent();
    }

    public void moveFirstCaseToSection(String projectName, String sectionName) {
        log.info("Move the first test case to section '{}' in project '{}'", sectionName, projectName);
        openCases(projectName);
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.changeSectionTestCase(sectionName)
                .backToCases()
                .verifyCaseExistsInSection(caseTitle, sectionName);
    }

    public void fillFirstCaseCustomField(String projectName, String value) {
        log.info("Fill the custom field of the first test case in project '{}'", projectName);
        openCases(projectName);
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.fillCustomFieldTestCase(value);
    }

    public void changeFirstCasePriorityAndCheckHistory(String projectName) {
        log.info("Change priority and verify it in the history of the first case in project '{}'", projectName);
        openCases(projectName);
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        String newPriority = testCasesPage.changePriorityToDifferent();
        testCasePage.openHistory()
                .historyLatestContains("Priority", newPriority);
    }
}
