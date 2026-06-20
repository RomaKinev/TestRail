package pages;

import dto.TestCase;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TestCaseCreatePage {

    private static final Logger log = LogManager.getLogger(TestCaseCreatePage.class);

    public final String ADD_TEST_CASE_PAGE_TITLE = "[data-testid='testCaseContentHeaderTitle']";
    public final String TEST_CASE_INPUT_TITLE = "[data-testid='addEditCaseTitle']";
    public final String TEST_CASE_DROP_DOWN_SECTION = "[data-testid='editCaseSectionId']";
    public final String TEST_CASE_DROP_DOWN_TEMPLATE = "[data-testid='editCaseTemplateId']";
    public final String TEST_CASE_DROP_DOWN_TYPE = "[data-testid='editCaseTypeId']";
    public final String TEST_CASE_DROP_DOWN_PRIORITY = "[data-testid='editCasePriorityId']";
    public final String TEST_CASE_ADD_BUTTON = "[id='accept']";

    @Step("Проверяем, что открыта форма создания тест-кейса")
    public TestCaseCreatePage isPageOpen() {
        log.info("Проверяем, что открыта форма создания тест-кейса");
        $(ADD_TEST_CASE_PAGE_TITLE).shouldBe(visible);
        return this;
    }
    @Step("Создаём тест-кейс '{testCase.title}'")
    public TestCasePage createTestCase(TestCase testCase) {
        log.info("Создаём тест-кейс '{}'", testCase.getTitle());
        $(TEST_CASE_INPUT_TITLE).setValue(testCase.getTitle());
        $(TEST_CASE_ADD_BUTTON).click();
        return new TestCasePage();
    }

    @Step("Редактируем тест-кейс '{0}'")
    public TestCasePage editTestCase(String newTestCaseTitle) {
        log.info("Редактируем тест-кейс '{}'", newTestCaseTitle);
        $(TEST_CASE_INPUT_TITLE).clear();
        $(TEST_CASE_INPUT_TITLE).setValue(newTestCaseTitle);
        $(TEST_CASE_ADD_BUTTON).click();
        return new TestCasePage();
    }

    @Step("Смена приоритета тест-кейса на '{0}'")
    public TestCasePage changePriority(String newPriority) {
        log.info("Смена приоритета тест кейса на '{}'", newPriority);
        $(TEST_CASE_DROP_DOWN_PRIORITY).selectOption(newPriority);
        executeJavaScript("if(window.jQuery){jQuery(arguments[0]).trigger('change');}",
                $(TEST_CASE_DROP_DOWN_PRIORITY).toWebElement());
        $(TEST_CASE_ADD_BUTTON).click();
        return new TestCasePage();
    }

    @Step("Смена секции тест-кейса на '{0}'")
    public TestCasePage changeSection(String newSection) {
        log.info("Смена секции тест-кейса на '{}'", newSection);
        $(TEST_CASE_DROP_DOWN_SECTION).selectOption(newSection);
        executeJavaScript("if(window.jQuery){jQuery(arguments[0]).trigger('change');}",
                $(TEST_CASE_DROP_DOWN_SECTION).toWebElement());
        $(TEST_CASE_ADD_BUTTON).click();
        return new TestCasePage();
    }

    @Step("Считываем текущий приоритет")
    public String getCurrentPriority(){
        return $(TEST_CASE_DROP_DOWN_PRIORITY).getSelectedOptionText();
    }

}
