package pages;

import dto.TestCase;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class TestCasesPage {

    private static final Logger log = LogManager.getLogger(TestCasesPage.class);

    TestCaseCreatePage testCaseCreatePage = new TestCaseCreatePage();
    TestCasePage testCasePage = new TestCasePage();
    ProjectsPage projectsPage = new ProjectsPage();

    public static final String TEST_CASES_TITLE = "//span[@id='sectionName-42']";
    public static final String ADD_TEST_CASE = "[data-testid='sidebarCasesAdd']";
    public static final String TEST_CASE_NAME = "//span[text()='%s']";
    public static final String TEST_CASE_CHECKBOX = "//span[text()='%s']/ancestor::tr//input[@type='checkbox']";
    public static final String EDIT_TEST_CASE_BUTTON1 = "[data-testid='testCaseEditButton']";
    public static final String DELETE_TEST_CASE_BUTTON = "//span[text()='%s']/ancestor::tr//a[@class='deleteLink']";
    public static final String DELETE_FIRST_WINDOW = "[data-testid='casesDeletionDialog']";
    public static final String DELETE_PERMANENTLY_BUTTON = "[data-testid='casesDeletionDialog'] [data-testid='deleteCaseDialogActionSecondary']";
    public static final String DELETE_SECOND_WINDOW = "[data-testid='casesDeletionConfirmationDialog']";
    public static final String DELETE_CONFIRM_BUTTON = "[data-testid='casesDeletionConfirmationDialog'] [data-testid='deleteCaseDialogActionDefault']";

    // --- Case presence in a section ---
    public static final String CASE_IN_SECTION =
            "//div[contains(@class,'grid-container')]" +
                    "[.//span[contains(@class,'group-toggle-title') and normalize-space(text())='%s']]" +
                    "//span[@data-testid='sectionCaseTitle' and normalize-space(text())='%s']";
    public static final String ANY_CASE_TITLE = "[data-testid='sectionCaseTitle']";

    @Step("Verify the test cases page is open")
    public TestCasesPage isPageOpen() {
        log.info("Verify the test cases page is open");
        $x(TEST_CASES_TITLE).shouldBe(visible);
        return this;
    }

    @Step("Add test case '{0.title}'")
    public TestCasePage addTestCase(TestCase testCase) {
        log.info("Add test case '{}'", testCase.getTitle());
        $(ADD_TEST_CASE).click();
        testCaseCreatePage.isPageOpen()
                .createTestCase(testCase);
        return new TestCasePage();
    }

    @Step("Create and delete test case '{1.title}' in project '{0}'")
    public TestCasesPage addAndDeleteTestCase(String projectName, TestCase testCase) {
        String testCaseName = testCase.getTitle();
        log.info("Create and delete test case '{}' in project '{}'", testCaseName, projectName);
        $(ADD_TEST_CASE).click();
        testCaseCreatePage.isPageOpen()
                .createTestCase(testCase)
                .isCaseCreated();
        projectsPage.open()
                .openTestCasesByProject(projectName);
        $x(String.format(TEST_CASE_CHECKBOX, testCaseName)).click();
        $x(String.format(DELETE_TEST_CASE_BUTTON, testCaseName)).click();
        $(DELETE_FIRST_WINDOW).shouldBe(visible);
        $(DELETE_PERMANENTLY_BUTTON).click();
        $(DELETE_SECOND_WINDOW).shouldBe(visible);
        $(DELETE_CONFIRM_BUTTON).click();
        return this;
    }

    @Step("Verify test case '{0}' is not displayed")
    public TestCasesPage isTestCaseNotVisible(String testCaseName) {
        log.info("Verify test case '{}' is not displayed", testCaseName);
        $x(String.format(TEST_CASE_NAME, testCaseName)).shouldNot(visible);
        return this;
    }

    @Step("Get the title of the first test case in the list")
    public String getFirstCaseTitle() {
        String title = $$(ANY_CASE_TITLE).first().shouldBe(visible).getText().trim();
        log.info("First test case in the list: '{}'", title);
        return title;
    }

    @Step("Open test case '{0}'")
    public TestCasePage openTestCase(String testCaseName) {
        log.info("Open test case '{}'", testCaseName);
        $x(String.format(TEST_CASE_NAME, testCaseName)).click();
        return new TestCasePage();
    }

    @Step("Edit test case")
    public TestCasePage editTestCase(String testCaseName, String newTestCaseTitle) {
        log.info("Edit test case '{}'", testCaseName);
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen()
                .editTestCase(newTestCaseTitle)
                .isCaseUpdated();
        return new TestCasePage();
    }

    @Step("Change test case priority")
    public TestCasePage changePriorityTestCase(String newPriority) {
        log.info("Change test case priority to '{}'", newPriority);
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen()
                .changePriority(newPriority)
                .isCaseUpdated();
        return new TestCasePage();
    }

    @Step("Move test case to section '{0}'")
    public TestCasePage changeSectionTestCase(String newSection) {
        log.info("Move test case to section '{}'", newSection);
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen()
                .changeSection(newSection)
                .isCaseUpdated();
        return new TestCasePage();
    }

    @Step("Verify case '{0}' is in section '{1}'")
    public TestCasesPage verifyCaseExistsInSection(String caseTitle, String sectionName) {
        log.info("Verify case '{}' is in section '{}'", caseTitle, sectionName);
        $x(String.format(CASE_IN_SECTION, sectionName, caseTitle)).shouldBe(visible);
        return this;
    }

    @Step("Change priority to a different one")
    public String changePriorityToDifferent() {
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen();
        String current = testCaseCreatePage.getCurrentPriority();
        String newPriority = current.equalsIgnoreCase("High") ? "Low" : "High";
        testCaseCreatePage.changePriority(newPriority).isCaseUpdated();
        return newPriority;
    }
}
