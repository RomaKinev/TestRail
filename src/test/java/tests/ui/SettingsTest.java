package tests.ui;

import listeners.RetryAnalyzer;
import ui.dto.User;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.UserFactory.getUser;


public class SettingsTest extends BaseUITest {

    @Owner("Pavel")
    @Feature("Settings")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update user's name")
    @Test(
            testName = "Verify user's name can be updated",
            description = "Verify user's name can be updated",
            groups = "ui",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void updateUsersName() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsPage
                .changeUsersName(user);
    }

    @Owner("Pavel")
    @Feature("Settings")
    @Severity(SeverityLevel.NORMAL)
    @Description("Change language")
    @Test(
            testName = "Verify language can be changed",
            description = "Verify language can be changed",
            groups = "ui",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void changeLanguage() {

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsStep
                .changeLanguage();
    }

    @Owner("Pavel")
    @Feature("Settings")
    @Severity(SeverityLevel.NORMAL)
    @Description("Change color scheme")
    @Test(
            testName = "Verify color scheme can be changed",
            description = "Verify color scheme can be changed",
            groups = "ui",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void changeColorScheme() {

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsStep
                .changeColorTheme();
    }
}