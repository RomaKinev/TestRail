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

    @Step("Проверяем, что тест-кейс успешно создан")
    public TestCasePage isCaseCreated() {
        log.info("Проверяем, что тест-кейс успешно создан");
        $(withText(SUCCESS_MESSAGE_AFTER_ADD_TEST_CASE.trim())).shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что тест-кейс успешно обновлен")
    public TestCasePage isCaseUpdated() {
        log.info("Проверяем, что тест-кейс успешно обновлен");
        $(withText(SUCCESS_MESSAGE_AFTER_UPDATE_TEST_CASE.trim())).shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что открыт тест-кейс '{0}'")
    public TestCasePage isCaseOpen(String testCaseTitle) {
        log.info("Проверяем, что открыт тест-кейс '{}'", testCaseTitle);
        $(TEST_CASE_NAME)
                .shouldBe(visible)
                .shouldHave(exactText(testCaseTitle));
        return this;
    }

    @Step("Переходим на страницу тест-кейсов проекта '{0}'")
    public TestCasesPage goToTestCasesPage(String projectName) {
        log.info("Переходим на страницу тест-кейсов проекта '{}'", projectName);
        return projectsPage.openTestCasesByProject(projectName);
    }

    @Step("Возвращаемся к списку тест-кейсов")
    public TestCasesPage backToCases() {
        log.info("Возвращаемся к списку тест-кейсов");
        $x(GO_TO_THE_PROJECT_TEST_CASES_BUTTON).click();
        return new TestCasesPage();
    }

    @Step("Открываем историю изменений")
    public TestCasePage openHistory() {
        log.info("Открываем историю изменений");
        $(HISTORY_BUTTON).click();
        $("#history").shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что в истории есть изменение '{0}' со значением '{1}'")
    public TestCasePage historyLatestContains(String field, String newValue) {
        log.info("Проверяем, что в истории есть изменение '{}' -> '{}'", field, newValue);
        $$(HISTORY_ITEM).first()
                .shouldHave(text(field))
                .shouldHave(text(newValue));
        return this;
    }
}
