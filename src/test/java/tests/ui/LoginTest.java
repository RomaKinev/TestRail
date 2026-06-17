package tests.ui;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        loginPage.open()
                .isPageOpen()
                .login("romakinev1+1@gmail.com", "Test123!");
        projectsPage.isPageOpen();
    }

    @Test
    public void logOutTest() {
        loginPage.open()
                .isPageOpen()
                .login("romakinev1+1@gmail.com", "Test123!");
        projectsPage.isPageOpen()
                .logOut()
                .isPageOpen();
    }
}
