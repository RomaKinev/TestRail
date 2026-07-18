package tests.ui;

import listeners.RetryAnalyzer;
import ui.dto.Group;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.GroupFactory.getGroup;


@Test(testName = "Group management functionality tests")
public class GroupTest extends BaseUITest {

    @Owner("Pavel")
    @Feature("Group Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create user group")
    @Test(
            testName = "Verify group can be created",
            description = "Verify group can be created",
            groups = "ui",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
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
    @Test(
            testName = "Verify group can be updated",
            description = "Verify group can be updated",
            groups = "ui",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
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
    @Test(
            testName = "Verify group can be deleted",
            description = "Verify group can be deleted",
            groups = "ui",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .deleteGroup(group);
    }
}