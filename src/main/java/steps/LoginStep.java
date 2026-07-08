package steps;

import pages.LoginPage;

public class LoginStep {

    LoginPage loginPage;

    public LoginStep(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void auth(String login, String password) {
        loginPage.open()
                .isPageOpen()
                .login(login, password);
    }
}
