package pages;

import dto.Run;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static dict.Elements.SUCCESS_MESSAGE_AFTER_ADD_TEST_RUN;

public class TestRunsPage {

    private static final Logger log = LogManager.getLogger(TestRunsPage.class);

    public static final String ADD_TEST_RUN_BUTTON = "[data-testid='navigationRunsAdd']";
    public static final String CHOOSE_SUITE_DIALOG = "#dialog-ident-chooseSuiteDialog";
    public static final String CHOOSE_SUITE_OK = "[data-testid='runSuiteOkButton']";
    public static final String RUN_NAME_INPUT = "[data-testid='addRunFormName']";
    public static final String INCLUDE_ALL_RADIO = "#includeAll";
    public static final String ADD_RUN_OK_BUTTON = "[data-testid='addRunFormOkButton']";
    public static final String SUCCESS_MESSAGE = "[data-testid='messageSuccessDivBox']";

    @Step("Открываем форму создания тест-рана")
    public TestRunsPage openAddRunForm() {
        log.info("Открываем форму создания тест-рана");
        $(ADD_TEST_RUN_BUTTON).click();
        // проект в режиме multiple suites — сначала диалог выбора сьюта
        $(CHOOSE_SUITE_DIALOG).shouldBe(visible);
        $(CHOOSE_SUITE_OK).click();
        $(RUN_NAME_INPUT).shouldBe(visible);
        return this;
    }

    @Step("Создаём тест-ран '{run.name}'")
    public TestRunsPage createRun(Run run) {
        log.info("Создаём тест-ран '{}'", run.getName());
        sleep(2000);
        $(RUN_NAME_INPUT).setValue(run.getName()).shouldHave(value(run.getName()));
        $(INCLUDE_ALL_RADIO).click();
        $(ADD_RUN_OK_BUTTON).click();
        return this;
    }

    @Step("Проверяем, что тест-ран успешно создан")
    public TestRunsPage isRunCreated() {
        log.info("Проверяем, что тест-ран успешно создан");
        $(SUCCESS_MESSAGE).shouldBe(visible)
                .shouldHave(text(SUCCESS_MESSAGE_AFTER_ADD_TEST_RUN));
        return this;
    }
}
