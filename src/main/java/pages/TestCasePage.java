package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.SUCCESS_MESSAGE_AFTER_ADD_TEST_CASE;
import static pages.TestCasesPage.TEST_CASES_TITLE;

public class TestCasePage {

    public final String TEST_CASE_NAME = "[data-testid = 'testCaseContentHeaderTitle']";

    ProjectsPage projectsPage = new ProjectsPage();
    private static final Logger log = LogManager.getLogger(TestCasePage.class);

    public static final String GO_TO_THE_PROJECT_TEST_CASES_BUTTON = "//a[@data-testid='navigateToCasesButton']";

    public TestCasePage isCaseCreated() {
        log.info("Проверяем, что тест-кейс успешно создан");
        $(withText(SUCCESS_MESSAGE_AFTER_ADD_TEST_CASE.trim())).shouldBe(visible);
        return this;
    }

    public TestCasePage isCaseOpen(String testCaseTitle) {
        log.info("Проверяем, что открыт тест-кейс '{}'", testCaseTitle);
        $(TEST_CASE_NAME)
                .shouldBe(visible)
                .shouldHave(exactText(testCaseTitle));
        return this;
    }

    public TestCasesPage goToTestCasesPage(String projectName) {
        log.info("Переходим на страницу тест-кейсов проекта '{}'", projectName);
        return projectsPage.openTestCasesByProject(projectName);
    }
}
