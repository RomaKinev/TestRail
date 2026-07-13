package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public class ReportsPage {

    private static final Logger log = LogManager.getLogger(ReportsPage.class);

    public static final String REPORTS_NAV = "#navigation-reports";
    public static final String COMPARISON_FOR_CASES = "Comparison for Cases";
    public static final String REPORT_NAME = "[data-testid='addReportName']";
    public static final String SUITE_SELECT = "#custom_runs_suites_id";
    public static final String ONLY_SELECTED_RUNS = "#custom_runs_include_selected";
    public static final String ADD_TEST_RUNS_LINK = "Add Test Runs";
    public static final String RUN_CHECKBOX = "input[name^='selected-']";
    public static final String ADD_RUNS_SUBMIT = "#addRunsSubmit";
    public static final String REPORT_SUBMIT = "#submit";

    @Step("Открываем шаблон отчёта 'Comparison for Cases'")
    public ReportsPage openComparisonForCasesReport() {
        log.info("Открываем шаблон отчёта 'Comparison for Cases'");
        $(REPORTS_NAV).click();
        $(byText(COMPARISON_FOR_CASES)).click();
        $(REPORT_NAME).shouldBe(visible);
        return this;
    }

    @Step("Выбираем сьют '{0}' и все раны проекта для сравнения")
    public ReportsPage selectSuiteAndAllRuns(String suiteName) {
        log.info("Выбираем сьют '{}' и все раны проекта для сравнения", suiteName);
        $(SUITE_SELECT).selectOption(suiteName);
        executeJavaScript("if(window.jQuery){jQuery(arguments[0]).trigger('change');}",
                $(SUITE_SELECT).toWebElement());
        sleep(2000); // ждём AJAX-перезагрузку секции ранов под выбранный сьют
        $(ONLY_SELECTED_RUNS).click(usingDefaultMethod());
        $(byText(ADD_TEST_RUNS_LINK)).click(usingDefaultMethod());
        $$(RUN_CHECKBOX).shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0));
        $$(RUN_CHECKBOX).forEach(cb -> cb.click(usingDefaultMethod()));
        $(ADD_RUNS_SUBMIT).click();
        sleep(1000);
        return this;
    }

    @Step("Генерируем отчёт сравнения")
    public ReportsPage generateReport() {
        log.info("Генерируем отчёт сравнения");
        $(REPORT_SUBMIT).click();
        return this;
    }

    @Step("Проверяем, что отчёт сравнения создан")
    public ReportsPage isReportCreated() {
        log.info("Проверяем, что отчёт сравнения создан");
        // успешная генерация уводит с формы создания отчёта
        $(REPORT_SUBMIT).shouldNot(exist);
        return this;
    }
}
