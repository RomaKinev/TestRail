package tests.ui;

import dto.TestCase;
import org.testng.annotations.Test;

import static dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create a new test case", groups = {"ui", "smoke"})
    public void testCaseCreationTest() {
        TestCase testCase = getTestCase();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.isPageOpen()
                .addTestCase(testCase);
        testCasePage.isCaseCreated();
    }

    @Test(description = "Create and then delete a test case", groups = {"ui"})
    public void testCaseCreationAndDeletionTest() {
        TestCase testCase = getTestCase();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.isPageOpen()
                .addAndDeleteTestCase("Test", testCase)
                .isTestCaseNotVisible(testCase.getTitle());
    }

    @Test(description = "Open an existing test case", groups = {"ui"})
    public void openTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.isPageOpen()
                .openTestCase("case")
                .isCaseOpen("case");
    }

    @Test(description = "Edit test case title via edit form", groups = {"ui"})
    public void editTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.editTestCase(caseTitle, caseTitle + "_edited");
    }

    @Test(description = "Change test case priority", groups = {"ui"})
    public void changePriorityInTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.changePriorityToDifferent();
    }

    @Test(description = "Move test case to another section via edit form", groups = {"ui"})
    public void moveTestCaseToSectionTest() {
        String targetSection = "section1";

        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        String caseTitle = testCasesPage.getFirstCaseTitle();
        testCasesPage.openTestCase(caseTitle);
        testCasesPage.changeSectionTestCase(targetSection)
                .backToCases()
                .verifyCaseExistsInSection(caseTitle, targetSection);
    }

    @Test(description = "Priority change is reflected in case history", groups = {"ui"})
    public void priorityChangeInHistoryTest() {
        String caseTitle = "case";

        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.openTestCase(caseTitle);
        String newPriority = testCasesPage.changePriorityToDifferent();
        testCasePage.openHistory()
                .historyLatestContains("Priority", newPriority);
    }
}
