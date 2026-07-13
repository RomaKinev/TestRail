package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public class TestRunPage {

    private static final Logger log = LogManager.getLogger(TestRunPage.class);

    public static final String TEST_TITLE_LINK = "a[href*='tests/view']:not(.link-noline)";
    public static final String ADD_RESULT_BUTTON = "#sidebar-tests-addresult";
    public static final String RESULT_STATUS_SELECT = "#addResultStatus";
    public static final String RESULT_COMMENT = "#addResultComment_display .fr-element[contenteditable='true']";
    public static final String RESULT_SUBMIT = "#addResultSubmit";
    public static final String RESULT_STATUS_LABEL = "[data-testid='testResultStatus']";
    public static final String ATTACH_ZONE = ".attachment-list-empty-add";
    public static final String FILE_INPUT = "input.dz-hidden-input";

    // --- Фильтр по статусу ---
    public static final String FILTER_TRIGGER = "#filterByChange";
    public static final String STATUS_FILTER_EXPAND = "[id='filter-tests:status_id'] a.link-noline";
    public static final String STATUS_FILTER_SELECT = "[id='filter-tests:status_id'] select";
    public static final String FILTER_APPLY = "[data-testid='filterTestsApply']";

    // --- Закрытие рана ---
    public static final String TOOLBAR_BUTTON = "a.toolbar-button";
    public static final String CLOSE_RUN_TEXT = "Close";
    public static final String CONFIRM_DIALOG = "#dialog-ident-confirmDialog";
    public static final String CONFIRM_YES = "#dialog-ident-confirmDialog a.button-ok";

    @Step("Открываем первый тест в ране")
    public TestRunPage openFirstTest() {
        log.info("Открываем первый тест в ране");
        $(TEST_TITLE_LINK).click();
        return this;
    }

    @Step("Добавляем результат '{0}' с комментарием '{1}'")
    public TestRunPage addResult(String status, String comment) {
        log.info("Добавляем результат '{}' с комментарием '{}'", status, comment);
        $(ADD_RESULT_BUTTON).click();
        sleep(1000);
        $(RESULT_STATUS_SELECT).selectOption(status);
        executeJavaScript("if(window.jQuery){jQuery(arguments[0]).trigger('change');}",
                $(RESULT_STATUS_SELECT).toWebElement());
        $(RESULT_COMMENT).click();
        $(RESULT_COMMENT).sendKeys(comment);
        $(RESULT_SUBMIT).click();
        return this;
    }

    @Step("Добавляем результат '{0}' с комментарием и вложением")
    public TestRunPage addResultWithAttachment(String status, String comment, File file) {
        log.info("Добавляем результат '{}' с вложением '{}'", status, file.getName());
        $(ADD_RESULT_BUTTON).click();
        sleep(1000);
        $(RESULT_STATUS_SELECT).selectOption(status);
        executeJavaScript("if(window.jQuery){jQuery(arguments[0]).trigger('change');}",
                $(RESULT_STATUS_SELECT).toWebElement());
        $(RESULT_COMMENT).click();
        $(RESULT_COMMENT).sendKeys(comment);
        // «+» перекрыт текстом зоны — кликаем напрямую через JS, чтобы Dropzone создал input
        executeJavaScript("arguments[0].click();", $(ATTACH_ZONE).toWebElement());
        $(FILE_INPUT).uploadFile(file);
        sleep(1000);
        $(RESULT_SUBMIT).click();
        return this;
    }

    @Step("Проверяем, что результат '{0}' сохранён")
    public TestRunPage isResultAdded(String status) {
        log.info("Проверяем, что результат '{}' сохранён", status);
        $(RESULT_STATUS_LABEL).shouldBe(visible).shouldHave(text(status));
        return this;
    }

    @Step("Фильтруем тесты по статусу '{0}'")
    public TestRunPage filterByStatus(String status) {
        log.info("Фильтруем тесты по статусу '{}'", status);
        $(FILTER_TRIGGER).click(usingDefaultMethod());
        $(STATUS_FILTER_EXPAND).click(usingDefaultMethod());
        $(STATUS_FILTER_SELECT).selectOption(status);
        $(FILTER_APPLY).click(usingDefaultMethod());
        return this;
    }

    @Step("Проверяем, что тест '{0}' виден в списке")
    public TestRunPage isTestVisible(String title) {
        log.info("Проверяем, что тест '{}' виден", title);
        $(byText(title)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что тест '{0}' скрыт фильтром")
    public TestRunPage isTestHidden(String title) {
        log.info("Проверяем, что тест '{}' скрыт фильтром", title);
        $(byText(title)).shouldNot(exist);
        return this;
    }

    @Step("Закрываем тест-ран")
    public TestRunPage closeRun() {
        log.info("Закрываем тест-ран");
        $$(TOOLBAR_BUTTON).findBy(text(CLOSE_RUN_TEXT)).click();
        $(CONFIRM_DIALOG).shouldBe(visible);
        $(CONFIRM_YES).click();
        return this;
    }

    @Step("Проверяем, что ран закрыт (недоступен для редактирования)")
    public TestRunPage isRunClosed() {
        log.info("Проверяем, что ран закрыт");
        $$(TOOLBAR_BUTTON).findBy(text(CLOSE_RUN_TEXT)).shouldNot(exist);
        return this;
    }
}
