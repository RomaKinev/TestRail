package tests.ui;

import config.TestConfig;
import dto.User;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import static dto.UserFactory.getUser;


public class SettingsUITest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Test(priority = 1)
    public void updateUsersName() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsPage
                .changeUsersName(user);
    }

    @Test(priority = 2)
    public void changeLanguage() {

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsStep
                .changeLanguage();
    }

    @Test(priority = 3)
    public void changeColorScheme() {

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        settingsStep
                .changeColorTheme();
    }
}