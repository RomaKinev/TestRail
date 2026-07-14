package ui.pages;

import ui.dto.Suite;
import ui.dto.TestCase;
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
import static ui.dict.Elements.ADD_SUITE_PAGE_TITLE;

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

    @Step("Open the suite creation form")
    public TestSuitesPage openAddSuiteForm() {
        log.info("Open the suite creation form");
        $(ADD_SUITE_SIDEBAR).click();
        $(byText(ADD_SUITE_PAGE_TITLE)).shouldBe(visible);
        $(SUITE_NAME_INPUT).shouldBe(visible);
        $(SUITE_DESCRIPTION_INPUT).shouldBe(visible);
        return this;
    }

    @Step("Create suite '{suite.name}'")
    public TestSuitesPage createSuite(Suite suite) {
        log.info("Create suite '{}'", suite.getName());
        $(SUITE_DESCRIPTION_INPUT).click();
        $(SUITE_DESCRIPTION_INPUT).sendKeys(suite.getDescription());
        $(SUITE_NAME_INPUT).setValue(suite.getName()).shouldHave(value(suite.getName()));
        $(SUITE_SAVE_BUTTON).click();
        return this;
    }

    @Step("Verify the suite is created successfully")
    public TestSuitesPage isSuiteCreated() {
        log.info("Verify the suite is created successfully");
        $(SUCCESS_MESSAGE).shouldBe(visible)
                .shouldHave(text("Successfully added the new test suite"));
        return this;
    }

    @Step("Verify suite '{0}' is in the list")
    public TestSuitesPage isSuiteVisible(String suiteName) {
        log.info("Verify suite '{}' is in the list", suiteName);
        $(By.xpath(String.format(SUITE_ROW, suiteName))).shouldBe(visible);
        return this;
    }

    @Step("Add test case '{testCase.title}' to suite")
    public TestCasePage addTestCaseToSuite(TestCase testCase) {
        log.info("Add test case '{}' to suite", testCase.getTitle());
        $(ADD_TEST_CASE_IN_SUITE).click();
        return testCaseCreatePage.isPageOpen().createTestCase(testCase);
    }

    @Step("Verify suite '{0}' shows cases count: {1}")
    public TestSuitesPage hasCasesCount(String suiteName, int count) {
        log.info("Verify suite case count '{}': {}", suiteName, count);
        $(By.xpath(String.format(SUITE_DESCRIPTION, suiteName)))
                .shouldHave(text(count + " test case"));
        return this;
    }

    @Step("Open editing of suite '{0}'")
    public TestSuitesPage openSuiteEdit(String suiteName) {
        log.info("Open editing of suite '{}'", suiteName);
        $x(String.format(SUITE_EDIT_LINK, suiteName)).click();
        return this;
    }

    @Step("Delete suite")
    public TestSuitesPage deleteSuite() {
        log.info("Delete suite");
        $(byText(DELETE_SUITE_BUTTON)).click();
        $(DELETE_CONFIRM_CHECKBOX).click();
        $(DELETE_CONFIRM_OK).click();
        return this;
    }

    @Step("Verify suite '{0}' is deleted")
    public TestSuitesPage isSuiteDeleted(String suiteName) {
        log.info("Verify suite '{}' is deleted", suiteName);
        $(SUCCESS_MESSAGE).shouldBe(visible).shouldHave(text("Successfully deleted the test suite"));
        return this;
    }
}
