package ui.pages;

import ui.dto.TestRun;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.SUCCESS_MESSAGE_AFTER_ADD_TEST_RUN;


public class TestRunsPage extends BasePage {

    public static final String ADD_TEST_RUN_BUTTON = "[data-testid='navigationRunsAdd']";
    public static final String CHOOSE_SUITE_DIALOG = "#dialog-ident-chooseSuiteDialog";
    public static final String CHOOSE_SUITE_OK = "[data-testid='runSuiteOkButton']";
    public static final String RUN_NAME_INPUT = "[data-testid='addRunFormName']";
    public static final String INCLUDE_ALL_RADIO = "#includeAll";
    public static final String ADD_RUN_OK_BUTTON = "[data-testid='addRunFormOkButton']";

    @Step("Open the test run creation form")
    public TestRunsPage openAddRunForm() {
        log.info("Open the test run creation form");
        $(ADD_TEST_RUN_BUTTON).click();
        // multiple-suites project — the suite selection dialog appears first
        $(CHOOSE_SUITE_DIALOG).shouldBe(visible);
        $(CHOOSE_SUITE_OK).click();
        $(RUN_NAME_INPUT).shouldBe(visible);

        return this;
    }

    @Step("Create test run '{run.name}'")
    public TestRunsPage createRun(TestRun run) {
        log.info("Create test run '{}'", run.getName());
        sleep(2000);
        $(RUN_NAME_INPUT).setValue(run.getName()).shouldHave(value(run.getName()));
        $(INCLUDE_ALL_RADIO).click();
        $(ADD_RUN_OK_BUTTON).click();

        return this;
    }

    @Step("Verify the test run is created successfully")
    public TestRunsPage isRunCreated() {
        log.info("Verify the test run is created successfully");
        $(SUCCESS_MESSAGE_BOX).shouldBe(visible)
                .shouldHave(text(SUCCESS_MESSAGE_AFTER_ADD_TEST_RUN));

        return this;
    }
}
