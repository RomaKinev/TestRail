package steps;

import dto.User;
import lombok.extern.log4j.Log4j2;
import pages.*;


@Log4j2
public class UsersNRolesStep {

    UsersNRolesPage usersNRolesPage;

    public UsersNRolesStep(UsersNRolesPage usersNRolesPage) {
        this.usersNRolesPage = usersNRolesPage;
    }

    public void createUser(User user) {
        usersNRolesPage
                .addUser(user);
    }

    public void updateUser(User user) {
        usersNRolesPage
                .updateUserInformation(user);
    }

    public void deactivateUser(User user) {
        usersNRolesPage
                .deactivateUser(user);
    }
}