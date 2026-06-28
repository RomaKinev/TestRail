package pages;

import dto.Suite;
import dto.TestCase;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.ADD_SUITE_PAGE_TITLE;

public class TestSuitesPage {

    TestCaseCreatePage testCaseCreatePage = new TestCaseCreatePage();

    private static final Logger log = LogManager.getLogger(TestSuitesPage.class);

    public static final String ADD_SUITE_SIDEBAR = "a[href*='suites/add']";
    public static final String SUITE_NAME_INPUT = "[data-testid='addSuiteName']";
    public static final String SUITE_DESCRIPTION_INPUT =
            "[data-testid='editSectionDescription'] .fr-element[contenteditable='true']";
    public static final String SUITE_SAVE_BUTTON = "[data-testid='addSuiteSaveButton']";
    public static final String SUCCESS_MESSAGE = "[data-testid='messageSuccessDivBox']";
    public static final String SUITE_ROW = "//div[contains(@class,'flex-suites-row')]" +
            "[.//div[@data-testid='runNameSummary']//a[normalize-space(text())='%s']]";
    public static final String SUITE_DESCRIPTION = SUITE_ROW + "//div[contains(@class,'summary-description')]";
    public static final String SUITE_EDIT_LINK = SUITE_ROW + "//div[contains(@class,'summary-links')]//a[normalize-space(text())='Edit']";
    public static final String DELETE_SUITE_BUTTON = "Delete Test Suite";
    public static final String DELETE_CONFIRM_CHECKBOX = "[data-testid='caseFieldsTabDeleteDialogCheckbox'] label";
    public static final String DELETE_CONFIRM_OK = "[data-testid='caseFieldsTabDeleteDialogButtonOk']";
    public static final String ADD_TEST_CASE_IN_SUITE = "a.button-add[href*='cases/add']";

    @Step("Открываем форму создания сьюта")
    public TestSuitesPage openAddSuiteForm() {
        log.info("Открываем форму создания сьюта");
        $(ADD_SUITE_SIDEBAR).click();
        $(byText(ADD_SUITE_PAGE_TITLE)).shouldBe(visible);
        $(SUITE_NAME_INPUT).shouldBe(visible);
        $(SUITE_DESCRIPTION_INPUT).shouldBe(visible);
        return this;
    }

    @Step("Создаём сьют '{suite.name}'")
    public TestSuitesPage createSuite(Suite suite) {
        log.info("Создаём сьют '{}'", suite.getName());
        $(SUITE_DESCRIPTION_INPUT).click();
        $(SUITE_DESCRIPTION_INPUT).sendKeys(suite.getDescription());
        $(SUITE_NAME_INPUT).setValue(suite.getName()).shouldHave(value(suite.getName()));
        $(SUITE_SAVE_BUTTON).click();
        return this;
    }

    @Step("Проверяем, что сьют успешно создан")
    public TestSuitesPage isSuiteCreated() {
        log.info("Проверяем, что сьют успешно создан");
        $(SUCCESS_MESSAGE).shouldBe(visible)
                .shouldHave(text("Successfully added the new test suite"));
        return this;
    }

    @Step("Проверяем, что сьют '{0}' есть в списке")
    public TestSuitesPage isSuiteVisible(String suiteName) {
        log.info("Проверяем, что сьют '{}' есть в списке", suiteName);
        $(By.xpath(String.format(SUITE_ROW, suiteName))).shouldBe(visible);
        return this;
    }

    @Step("Добавляем тест-кейс '{testCase.title}' в сьют")
    public TestCasePage addTestCaseToSuite(TestCase testCase) {
        log.info("Добавляем тест-кейс '{}' в сьют", testCase.getTitle());
        $(ADD_TEST_CASE_IN_SUITE).click();
        return testCaseCreatePage.isPageOpen().createTestCase(testCase);
    }

    @Step("Проверяем, что в сьюте '{0}' указано кейсов: {1}")
    public TestSuitesPage hasCasesCount(String suiteName, int count) {
        log.info("Проверяем счётчик кейсов сьюта '{}': {}", suiteName, count);
        $(By.xpath(String.format(SUITE_DESCRIPTION, suiteName)))
                .shouldHave(text(count + " test case"));
        return this;
    }

    @Step("Открываем редактирование сьюта '{0}'")
    public TestSuitesPage openSuiteEdit(String suiteName) {
        log.info("Открываем редактирование сьюта '{}'", suiteName);
        $x(String.format(SUITE_EDIT_LINK, suiteName)).click();
        return this;
    }

    @Step("Удаляем сьют")
    public TestSuitesPage deleteSuite() {
        log.info("Удаляем сьют");
        $(byText(DELETE_SUITE_BUTTON)).click();
        $(DELETE_CONFIRM_CHECKBOX).click();
        $(DELETE_CONFIRM_OK).click();
        return this;
    }

    @Step("Проверяем, что сьют '{0}' удалён")
    public TestSuitesPage isSuiteDeleted(String suiteName) {
        log.info("Проверяем, что сьют '{}' удалён", suiteName);
        $(SUCCESS_MESSAGE).shouldBe(visible).shouldHave(text("Successfully deleted the test suite"));
        return this;
    }
}
