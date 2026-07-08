package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dict.Elements;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.LOGIN_PAGE;

public class LoginPage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public final String LOGIN = "[data-testid='loginIdName']";
    public final String PASSWORD = "[data-testid='loginPasswordFormDialog']";
    public final String LOG_IN_BUTTON = "[data-testid='loginButtonPrimary']";
    public final String ERROR_MESSAGE = "[data-testid='loginErrorText']";

    @Step("Проверяем, что открыта страница логина")
    public LoginPage isPageOpen() {
        log.info("Проверяем, что открыта страница логина");
        $(byText(LOGIN_PAGE)).shouldBe(visible);
        return this;
    }

    @Step("Открываем страницу логина")
    public LoginPage open() {
        log.info("Открываем страницу логина");
        Selenide.open("/index.php?/auth/login/");
        return this;
    }

    @Step("Логинимся под пользователем '{0}'")
    public ProjectsPage login(String login, String password) {
        log.info("Логинимся под пользователем '{}'", login);
        $(LOGIN).setValue(login);
        $(PASSWORD).setValue(password);
        $(LOG_IN_BUTTON).click();
        return new ProjectsPage();
    }

    @Step("Логинимся с невалидными данными под '{0}'")
    public LoginPage loginWithError(String login, String password) {
        log.info("Логинимся с невалидными данными под '{}'", login);
        $(LOGIN).setValue(login);
        $(PASSWORD).setValue(password);
        $(LOG_IN_BUTTON).click();
        return this;
    }

    @Step("Проверяем сообщение об ошибке: '{0}'")
    public LoginPage shouldShowError(String expectedText) {
        log.info("Проверяем сообщение об ошибке: '{}'", expectedText);
        $(ERROR_MESSAGE).shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
