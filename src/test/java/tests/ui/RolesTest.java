package tests.ui;

import config.TestConfig;
import listeners.RetryAnalyzer;
import ui.dto.Roles;
import org.aeonbits.owner.ConfigFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.RolesFactory.getRole;


public class RolesTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Owner("Pavel")
    @Feature("Role Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create role")
    @Test(
            testName = "Verify role can be created",
            description = "Verify role can be created",
            groups = "ui",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void createRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .createRole(role);
    }

    @Owner("Pavel")
    @Feature("Role Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update role")
    @Test(
            testName = "Verify role can be updated",
            description = "Verify role can be updated",
            groups = "ui",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void updateRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .updateRole(role);
    }

    @Owner("Pavel")
    @Feature("Role Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete role")
    @Test(
            testName = "Verify role can be deleted",
            description = "Verify role can be deleted",
            groups = "ui",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .deleteRole(role);
    }
}