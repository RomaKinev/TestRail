package tests.ui;

import config.TestConfig;
import dto.User;
import org.aeonbits.owner.ConfigFactory;

import org.testng.annotations.Test;

import static dto.UserFactory.getUser;


public class UserTest extends BaseTest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Test
    public void createUserTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .createUser(user);
    }

    @Test
    public void updateUserDataTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .updateUser(user);
    }

    @Test
    public void deactivateUserTest() {
        User user = getUser();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        usersNRolesStep
                .deactivateUser(user);
    }
}