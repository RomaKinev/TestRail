package tests.ui;

import config.TestConfig;
import dto.Group;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import static dto.GroupFactory.getGroup;


public class GroupTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Test(priority = 1)
    public void createGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .createGroup(group);
    }

    @Test(priority = 2)
    public void updateGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .updateGroup(group);
    }

    @Test(priority = 3)
    public void deleteGroupTest() {
        Group group = getGroup();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        groupStep
                .deleteGroup(group);
    }
}