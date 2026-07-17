package ui.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.LOGIN_PAGE;


public class LoginPage extends BasePage {

    public final String LOGIN = "[data-testid='loginIdName']";
    public final String PASSWORD = "[data-testid='loginPasswordFormDialog']";
    public final String LOG_IN_BUTTON = "[data-testid='loginButtonPrimary']";
    public final String ERROR_MESSAGE = "[data-testid='loginErrorText']";

    @Step("Verify the login page is open")
    public LoginPage isPageOpen() {
        log.info("Verify the login page is open");
        $(byText(LOGIN_PAGE)).shouldBe(visible);

        return this;
    }

    @Step("Open the login page")
    public LoginPage open() {
        log.info("Open the login page");
        Selenide.open("/index.php?/auth/login/");

        return this;
    }

    @Step("Log in as user '{0}'")
    public ProjectsPage login(String login, String password) {
        log.info("Log in as user '{}'", login);
        sleep(2000);
        $(LOGIN).setValue(login);
        $(PASSWORD).setValue(password);
        $(LOG_IN_BUTTON).click();

        return new ProjectsPage();
    }

    @Step("Log in with invalid credentials as '{0}'")
    public LoginPage loginWithError(String login, String password) {
        log.info("Log in with invalid credentials as '{}'", login);
        $(LOGIN).setValue(login);
        $(PASSWORD).setValue(password);
        $(LOG_IN_BUTTON).click();

        return this;
    }

    @Step("Verify error message: '{0}'")
    public LoginPage shouldShowError(String expectedText) {
        log.info("Verify error message: '{}'", expectedText);
        $(ERROR_MESSAGE).shouldBe(visible).shouldHave(text(expectedText));

        return this;
    }
}