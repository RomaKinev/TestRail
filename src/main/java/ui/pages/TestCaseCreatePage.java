package ui.pages;

import ui.dto.TestCase;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class TestCaseCreatePage extends BasePage {

    public final String TEST_CASE_INPUT_TITLE = "[data-testid='addEditCaseTitle']";
    public final String TEST_CASE_DROP_DOWN_SECTION = "[data-testid='editCaseSectionId']";
    public final String TEST_CASE_DROP_DOWN_PRIORITY = "[data-testid='editCasePriorityId']";
    public final String TEST_CASE_ADD_BUTTON = "[id='accept']";

    @Step("Verify the test case creation form is open")
    public TestCaseCreatePage isPageOpen() {
        log.info("Verify the test case creation form is open");
        $(CONTENT_HEADER_TITLE).shouldBe(visible);

        return this;
    }

    @Step("Create test case '{testCase.title}'")
    public TestCasePage createTestCase(TestCase testCase) {
        log.info("Create test case '{}'", testCase.getTitle());
        sleep(2000);
        $(TEST_CASE_INPUT_TITLE).setValue(testCase.getTitle());
        $(TEST_CASE_ADD_BUTTON).click();

        return new TestCasePage();
    }

    @Step("Edit test case '{0}'")
    public TestCasePage editTestCase(String newTestCaseTitle) {
        log.info("Edit test case '{}'", newTestCaseTitle);
        $(TEST_CASE_INPUT_TITLE).clear();
        $(TEST_CASE_INPUT_TITLE).setValue(newTestCaseTitle);
        $(TEST_CASE_ADD_BUTTON).click();

        return new TestCasePage();
    }

    @Step("Change test case priority to '{0}'")
    public TestCasePage changePriority(String newPriority) {
        log.info("Change test case priority to '{}'", newPriority);
        $(TEST_CASE_DROP_DOWN_PRIORITY).selectOption(newPriority);
        triggerChange($(TEST_CASE_DROP_DOWN_PRIORITY));
        $(TEST_CASE_ADD_BUTTON).click();

        return new TestCasePage();
    }

    @Step("Change test case section to '{0}'")
    public TestCasePage changeSection(String newSection) {
        log.info("Change test case section to '{}'", newSection);
        $(TEST_CASE_DROP_DOWN_SECTION).selectOption(newSection);
        triggerChange($(TEST_CASE_DROP_DOWN_SECTION));
        $(TEST_CASE_ADD_BUTTON).click();

        return new TestCasePage();
    }

    @Step("Read the current priority")
    public String getCurrentPriority(){
        return $(TEST_CASE_DROP_DOWN_PRIORITY).getSelectedOptionText();
    }
}
