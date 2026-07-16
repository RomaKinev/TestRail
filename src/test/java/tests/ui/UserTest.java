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


public class UserTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Owner("Pavel")
    @Feature("User Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create user")
    @Test
    public void createUserTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .createUser(user);
    }

    @Owner("Pavel")
    @Feature("User Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update user data")
    @Test
    public void updateUserDataTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .updateUser(user);
    }

    @Owner("Pavel")
    @Feature("User Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Deactivate user")
    @Test
    public void deactivateUserTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .deactivateUser(user);
    }
}