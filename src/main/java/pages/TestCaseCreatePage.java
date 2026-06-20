package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

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
    @Step("Создаём тест-кейс '{0}'")
    public TestCasePage createTestCase(String testCaseTitle) {
        log.info("Создаём тест-кейс '{}'", testCaseTitle);
        $(TEST_CASE_INPUT_TITLE).setValue(testCaseTitle);
        $(TEST_CASE_ADD_BUTTON).click();
        return new TestCasePage();
    }

}
