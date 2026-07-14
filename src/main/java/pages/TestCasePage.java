package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;
import static pages.TestCasesPage.TEST_CASES_TITLE;

public class TestCasePage {

    public final String TEST_CASE_NAME = "[data-testid = 'testCaseContentHeaderTitle']";
    public static final String HISTORY_BUTTON = "[data-testid='caseSidebarNavigationCasesHistory']";
    public static final String HISTORY_ITEM = "#history .change-item";

    ProjectsPage projectsPage = new ProjectsPage();
    private static final Logger log = LogManager.getLogger(TestCasePage.class);

    public static final String GO_TO_THE_PROJECT_TEST_CASES_BUTTON = "//a[@data-testid='navigateToCasesButton']";

    @Step("Verify the test case is created successfully")
    public TestCasePage isCaseCreated() {
        log.info("Verify the test case is created successfully");
        $(withText(SUCCESS_MESSAGE_AFTER_ADD_TEST_CASE.trim())).shouldBe(visible);
        return this;
    }

    @Step("Verify the test case is updated successfully")
    public TestCasePage isCaseUpdated() {
        log.info("Verify the test case is updated successfully");
        $(withText(SUCCESS_MESSAGE_AFTER_UPDATE_TEST_CASE.trim())).shouldBe(visible);
        return this;
    }

    @Step("Verify test case '{0}' is open")
    public TestCasePage isCaseOpen(String testCaseTitle) {
        log.info("Verify test case '{}' is open", testCaseTitle);
        $(TEST_CASE_NAME)
                .shouldBe(visible)
                .shouldHave(exactText(testCaseTitle));
        return this;
    }

    @Step("Go to the test cases page of project '{0}'")
    public TestCasesPage goToTestCasesPage(String projectName) {
        log.info("Go to the test cases page of project '{}'", projectName);
        return projectsPage.openTestCasesByProject(projectName);
    }

    @Step("Go back to the test cases list")
    public TestCasesPage backToCases() {
        log.info("Go back to the test cases list");
        $x(GO_TO_THE_PROJECT_TEST_CASES_BUTTON).click();
        return new TestCasesPage();
    }

    @Step("Open the change history")
    public TestCasePage openHistory() {
        log.info("Open the change history");
        $(HISTORY_BUTTON).click();
        $("#history").shouldBe(visible);
        return this;
    }

    @Step("Verify the history contains change '{0}' with value '{1}'")
    public TestCasePage historyLatestContains(String field, String newValue) {
        log.info("Verify the history contains change '{}' -> '{}'", field, newValue);
        $$(HISTORY_ITEM).first()
                .shouldHave(text(field))
                .shouldHave(text(newValue));
        return this;
    }
}
