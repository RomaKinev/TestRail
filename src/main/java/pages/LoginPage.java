package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dict.Elements;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.LOGIN_PAGE;

public class LoginPage {

    public final String LOGIN = "[data-testid='loginIdName']";
    public final String PASSWORD = "[data-testid='loginPasswordFormDialog']";
    public final String LOG_IN_BUTTON = "[data-testid='loginButtonPrimary']";
    public final String ERROR_MESSAGE = "[data-testid='loginErrorText']";

    public LoginPage isPageOpen() {
        $(byText(LOGIN_PAGE)).shouldBe(visible);
        return this;
    }

    public LoginPage open() {
        Selenide.open("/index.php?/auth/login/");
        return this;
    }

    public ProjectsPage login(String login, String password) {
        $(LOGIN).setValue(login);
        $(PASSWORD).setValue(password);
        $(LOG_IN_BUTTON).click();
        return new ProjectsPage();
    }

    public LoginPage loginWithError(String login, String password) {
        $(LOGIN).setValue(login);
        $(PASSWORD).setValue(password);
        $(LOG_IN_BUTTON).click();
        return this;
    }

    public LoginPage shouldShowError(String expectedText) {
        $(ERROR_MESSAGE).shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
