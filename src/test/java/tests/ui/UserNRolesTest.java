package tests.ui;

import listeners.RetryAnalyzer;
import ui.dto.User;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.UserFactory.getUser;


@Test(testName = "User management functionality tests")
public class UserNRolesTest extends BaseUITest {

    @Owner("Pavel")
    @Feature("User Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create user")
    @Test(
            testName = "Verify user can be created",
            description = "Verify user can be created",
            groups = "ui",
            priority = 1,
            enabled = false,
            retryAnalyzer = RetryAnalyzer.class
    )
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
    @Test(
            testName = "Verify user data can be updated",
            description = "Verify user data can be updated",
            groups = "ui",
            priority = 2,
            enabled = false,
            retryAnalyzer = RetryAnalyzer.class
    )
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
    @Test(
            testName = "Verify user can be deactivated",
            description = "Verify user can be deactivated",
            groups = "ui",
            priority = 3,
            enabled = false,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deactivateUserTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .deactivateUser(user);
    }
}