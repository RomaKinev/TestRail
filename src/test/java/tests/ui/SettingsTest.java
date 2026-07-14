package tests.ui;

import config.TestConfig;
import ui.dto.User;
import org.aeonbits.owner.ConfigFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.UserFactory.getUser;


public class SettingsTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Owner("Pavel")
    @Feature("Settings")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update user's name")
    @Test(priority = 1)
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
    @Test(priority = 2)
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
    @Test(priority = 3)
    public void changeColorScheme() {

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsStep
                .changeColorTheme();
    }
}