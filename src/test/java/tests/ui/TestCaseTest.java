package tests.ui;

import ui.dto.TestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.TestCaseFactory.getTestCase;
import static tests.ui.LoginTest.CONFIG;

public class TestCaseTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new test case")
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

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create and then delete a test case")
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

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open an existing test case")
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

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit test case title via edit form")
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

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Change test case priority")
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

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Move test case to another section via edit form")
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

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Priority change is reflected in case history")
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
