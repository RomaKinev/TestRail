package tests.ui;

import config.TestConfig;
import ui.dto.Roles;
import org.aeonbits.owner.ConfigFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.RolesFactory.getRole;


public class RolesTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Owner("Pavel")
    @Feature("Role Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create role")
    @Test(priority = 1)
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
    @Test(priority = 2)
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
    @Test(priority = 3)
    public void deleteRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .deleteRole(role);
    }
}