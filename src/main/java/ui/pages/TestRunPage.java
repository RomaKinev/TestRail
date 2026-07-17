package ui.pages;

import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TestRunPage extends BasePage {

    public static final String TEST_TITLE_LINK = "a[href*='tests/view']:not(.link-noline)";
    public static final String ADD_RESULT_BUTTON = "#sidebar-tests-addresult";
    public static final String RESULT_STATUS_SELECT = "#addResultStatus";
    public static final String RESULT_COMMENT = "#addResultComment_display .fr-element[contenteditable='true']";
    public static final String RESULT_SUBMIT = "#addResultSubmit";
    public static final String RESULT_STATUS_LABEL = "[data-testid='testResultStatus']";
    public static final String ATTACH_ZONE = ".attachment-list-empty-add";
    public static final String FILE_INPUT = "input.dz-hidden-input";

    // --- Filter by status ---
    public static final String FILTER_TRIGGER = "#filterByChange";
    public static final String STATUS_FILTER_EXPAND = "[id='filter-tests:status_id'] a.link-noline";
    public static final String STATUS_FILTER_SELECT = "[id='filter-tests:status_id'] select";
    public static final String FILTER_APPLY = "[data-testid='filterTestsApply']";

    // --- Statistics ---
    public static final String BACK_TO_RUN = "#navigation-runs-testsresults";
    public static final String STAT_LEGEND = ".chart-legend-name";

    // --- Export ---
    public static final String EXPORT_DROPDOWN = ".exportDropdownLink";
    public static final String EXPORT_MENU_LINK = "#exportDropdown a";
    public static final String EXPORT_EXCEL_TEXT = "Export to Excel";
    public static final String EXPORT_SUBMIT = "#exportSubmit";

    // --- Close run ---
    public static final String TOOLBAR_BUTTON = "a.toolbar-button";
    public static final String CLOSE_RUN_TEXT = "Close";
    public static final String CONFIRM_DIALOG = "#dialog-ident-confirmDialog";
    public static final String CONFIRM_YES = "#dialog-ident-confirmDialog a.button-ok";

    @Step("Open the first test in the run")
    public TestRunPage openFirstTest() {
        log.info("Open the first test in the run");
        $(TEST_TITLE_LINK).click();

        return this;
    }

    @Step("Add result '{0}' with comment '{1}'")
    public TestRunPage addResult(String status, String comment) {
        log.info("Add result '{}' with comment '{}'", status, comment);
        $(ADD_RESULT_BUTTON).click();
        sleep(1000);
        $(RESULT_STATUS_SELECT).selectOption(status);
        triggerChange($(RESULT_STATUS_SELECT));
        $(RESULT_COMMENT).click();
        $(RESULT_COMMENT).sendKeys(comment);
        $(RESULT_SUBMIT).click();

        return this;
    }

    @Step("Add result '{0}' with comment and attachment")
    public TestRunPage addResultWithAttachment(String status, String comment, File file) {
        log.info("Add result '{}' with attachment '{}'", status, file.getName());
        $(ADD_RESULT_BUTTON).click();
        sleep(1000);
        $(RESULT_STATUS_SELECT).selectOption(status);
        triggerChange($(RESULT_STATUS_SELECT));
        $(RESULT_COMMENT).click();
        $(RESULT_COMMENT).sendKeys(comment);
        // the '+' is covered by the zone text — click directly via JS so Dropzone creates the input
        executeJavaScript("arguments[0].click();", $(ATTACH_ZONE).toWebElement());
        $(FILE_INPUT).uploadFile(file);
        sleep(1000);
        $(RESULT_SUBMIT).click();

        return this;
    }

    @Step("Verify result '{0}' is saved")
    public TestRunPage isResultAdded(String status) {
        log.info("Verify result '{}' is saved", status);
        $(RESULT_STATUS_LABEL).shouldBe(visible).shouldHave(text(status));

        return this;
    }

    @Step("Go back to the run page")
    public TestRunPage backToRun() {
        log.info("Go back to the run page");
        $(BACK_TO_RUN).click();

        return this;
    }

    @Step("Verify run statistics: {1} '{0}'")
    public TestRunPage hasStatistic(String status, int count) {
        log.info("Verify run statistics: {} '{}'", count, status);
        $$(STAT_LEGEND).findBy(text(count + " " + status)).shouldBe(visible);

        return this;
    }

    @Step("Export run results to Excel")
    public File exportResultsToExcel() throws FileNotFoundException {
        log.info("Export run results to Excel");
        $(EXPORT_DROPDOWN).click();
        $$(EXPORT_MENU_LINK).findBy(text(EXPORT_EXCEL_TEXT)).click();
        $(EXPORT_SUBMIT).shouldBe(visible);

        return $(EXPORT_SUBMIT).download();
    }

    @Step("Filter tests by status '{0}'")
    public TestRunPage filterByStatus(String status) {
        log.info("Filter tests by status '{}'", status);
        $(FILTER_TRIGGER).click(usingDefaultMethod());
        $(STATUS_FILTER_EXPAND).click(usingDefaultMethod());
        $(STATUS_FILTER_SELECT).selectOption(status);
        $(FILTER_APPLY).click(usingDefaultMethod());

        return this;
    }

    @Step("Verify test '{0}' is visible in the list")
    public TestRunPage isTestVisible(String title) {
        log.info("Verify test '{}' is visible", title);
        $(byText(title)).shouldBe(visible);

        return this;
    }

    @Step("Verify test '{0}' is hidden by the filter")
    public TestRunPage isTestHidden(String title) {
        log.info("Verify test '{}' is hidden by the filter", title);
        $(byText(title)).shouldNot(exist);

        return this;
    }

    @Step("Close test run")
    public TestRunPage closeRun() {
        log.info("Close test run");
        $$(TOOLBAR_BUTTON).findBy(text(CLOSE_RUN_TEXT)).click();
        $(CONFIRM_DIALOG).shouldBe(visible);
        $(CONFIRM_YES).click();

        return this;
    }

    @Step("Verify the run is closed (not editable)")
    public TestRunPage isRunClosed() {
        log.info("Verify the run is closed");
        $$(TOOLBAR_BUTTON).findBy(text(CLOSE_RUN_TEXT)).shouldNot(exist);

        return this;
    }
}
