package ui.steps;

import ui.dto.User;
import lombok.extern.log4j.Log4j2;
import ui.pages.UsersNRolesPage;


@Log4j2
public class UsersNRolesStep {

    UsersNRolesPage usersNRolesPage;

    public UsersNRolesStep(UsersNRolesPage usersNRolesPage) {
        this.usersNRolesPage = usersNRolesPage;
    }

    public void createUser(User user) {
        usersNRolesPage
                .addUser(user)
                .deactivateUser(user);
    }

    public void updateUser(User user) {
        usersNRolesPage
                .addUser(user)
                .updateUserInformation(user);
    }

    public void deactivateUser(User user) {
        usersNRolesPage
                .addUser(user)
                .deactivateUser(user);
    }
}