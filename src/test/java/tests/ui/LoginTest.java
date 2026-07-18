package tests.ui;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.DataProvider;
import io.qameta.allure.*;
import io.qameta.allure.model.Parameter;
import org.testng.annotations.Test;

import static ui.dict.Elements.LOGIN_ERROR;


@Test(testName = "Authentication functionality tests")
public class LoginTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with valid credentials")
    @Test(description = "Login with valid credentials", groups = {"ui", "smoke"})
    public void loginTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen();
    }

    @Owner("Roma")
    @Feature("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Log out from the application")
    @Test(description = "Log out from the application", groups = {"ui"})
    public void logOutTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .logOut()
                .isPageOpen();
    }

    @DataProvider(name = "Invalid Credentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"invalid@email.com", CONFIG.password()},
                {CONFIG.email(), "invalidPassword"}
        };
    }

    @Owner("Roma")
    @Feature("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with invalid credentials shows error")
    @Test(description = "Login with invalid credentials shows error", dataProvider = "Invalid Credentials", groups = {"ui"})
    public void loginWithInvalidCredentialsTest(@Param(mode = Parameter.Mode.MASKED) String email,
                                                @Param(mode = Parameter.Mode.MASKED) String password) {
        loginPage.open()
                .isPageOpen()
                .loginWithError(email, password)
                .shouldShowError(LOGIN_ERROR);
    }

    @Owner("Roma")
    @Feature("Authentication")
    @Severity(SeverityLevel.NORMAL)
    @Description("Session expires after clearing browser cookies")
    @Test(description = "Session expires after clearing browser cookies", groups = {"ui"})
    public void sessionExpiredAfterCookieClear() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen();
        Selenide.clearBrowserCookies();
        projectsPage.open();
        loginPage.isPageOpen();
    }
}