package tests.ui;

import org.testng.annotations.Test;

import static tests.ui.LoginTest.CONFIG;

public class TestCaseTest extends BaseTest {

    @Test
    public void testCaseCreationTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.isPageOpen()
                .addTestCase();
        testCasePage.isCaseCreated();
    }

    @Test
    public void testCaseCreationAndDeletionTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.isPageOpen()
                .addAndDeleteTestCase("Test", "Test Case")
                .isTestCaseNotVisible("Test Case");
    }

    @Test
    public void openTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.isPageOpen()
                .openTestCase("case")
                .isCaseOpen("case");
    }

    @Test
    public void editTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .openTestCasesByProject("Test")
                .isPageOpen();
        testCasesPage.openTestCase("case");
        testCasesPage.editTestCase("case", "case1");
    }
}
