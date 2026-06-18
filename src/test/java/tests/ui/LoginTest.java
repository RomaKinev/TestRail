package tests.ui;

import com.codeborne.selenide.Selenide;
import config.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static dict.Elements.LOGIN_ERROR;

public class LoginTest extends BaseTest {

    private static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Test
    public void loginTest() {
        loginPage.open()
                .isPageOpen()
                .login(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen();
    }

    @Test
    public void logOutTest() {
        loginPage.open()
                .isPageOpen()
                .login(CONFIG.email(), CONFIG.password());
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

    @Test(dataProvider = "Invalid Credentials")
    public void loginWithInvalidCredentialsTest(String email, String password) {
        loginPage.open()
                .isPageOpen()
                .loginWithError(email, password)
                .shouldShowError(LOGIN_ERROR);
    }

    @Test
    public void sessionExpiredAfterCookieClear() {
        loginPage.open()
                .isPageOpen()
                .login(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen();
        Selenide.clearBrowserCookies();
        projectsPage.open();
        loginPage.isPageOpen();
    }
}
