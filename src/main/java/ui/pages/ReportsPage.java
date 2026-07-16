package ui.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.*;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class ReportsPage extends BasePage {

    public static final String REPORTS_NAV = "#navigation-reports";
    public static final String COMPARISON_FOR_CASES = "Comparison for Cases";
    public static final String REPORT_NAME = "[data-testid='addReportName']";
    public static final String SUITE_SELECT = "#custom_runs_suites_id";
    public static final String ONLY_SELECTED_RUNS = "#custom_runs_include_selected";
    public static final String ADD_TEST_RUNS_LINK = "Add Test Runs";
    public static final String RUN_CHECKBOX = "input[name^='selected-']";
    public static final String ADD_RUNS_SUBMIT = "#addRunsSubmit";
    public static final String REPORT_SUBMIT = "#submit";

    @Step("Open the 'Comparison for Cases' report template")
    public ReportsPage openComparisonForCasesReport() {
        log.info("Open the 'Comparison for Cases' report template");
        $(REPORTS_NAV).click();
        $(byText(COMPARISON_FOR_CASES)).click();
        $(REPORT_NAME).shouldBe(visible);

        return this;
    }

    @Step("Select suite '{0}' and all project runs for comparison")
    public ReportsPage selectSuiteAndAllRuns(String suiteName) {
        log.info("Select suite '{}' and all project runs for comparison", suiteName);
        $(SUITE_SELECT).selectOption(suiteName);
        triggerChange($(SUITE_SELECT));
        sleep(2000); // wait for the AJAX reload of the runs section for the selected suite
        $(ONLY_SELECTED_RUNS).click(usingDefaultMethod());
        $(byText(ADD_TEST_RUNS_LINK)).click(usingDefaultMethod());
        $$(RUN_CHECKBOX).shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0));
        $$(RUN_CHECKBOX).forEach(cb -> cb.click(usingDefaultMethod()));
        $(ADD_RUNS_SUBMIT).click();
        sleep(1000);

        return this;
    }

    @Step("Generate comparison report")
    public ReportsPage generateReport() {
        log.info("Generate comparison report");
        $(REPORT_SUBMIT).click();

        return this;
    }

    @Step("Verify the comparison report is created")
    public ReportsPage isReportCreated() {
        log.info("Verify the comparison report is created");
        // a successful generation navigates away from the report creation form
        $(REPORT_SUBMIT).shouldNot(exist);

        return this;
    }
}
