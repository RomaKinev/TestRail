package tests.ui;

import config.TestConfig;
import ui.dto.Group;
import org.aeonbits.owner.ConfigFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.GroupFactory.getGroup;


public class GroupTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Owner("Pavel")
    @Feature("Group Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create user group")
    @Test(priority = 1)
    public void createGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .createGroup(group);
    }

    @Owner("Pavel")
    @Feature("Group Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update user group")
    @Test(priority = 2)
    public void updateGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .updateGroup(group);
    }

    @Owner("Pavel")
    @Feature("Group Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete user group")
    @Test(priority = 3)
    public void deleteGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .deleteGroup(group);
    }
}