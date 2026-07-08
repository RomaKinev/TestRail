package tests.ui;

import config.TestConfig;
import dto.Roles;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import static dto.RolesFactory.getRole;


public class RolesTest extends BaseTest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Test(priority = 1)
    public void createRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .createRole(role);
    }

    @Test(priority = 2)
    public void updateRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .updateRole(role);
    }

    @Test(priority = 3)
    public void deleteRole() {
        Roles role = getRole();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        rolesSteps
                .deleteRole(role);
    }
}