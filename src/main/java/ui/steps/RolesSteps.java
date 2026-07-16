package ui.steps;

import ui.dto.Roles;
import ui.pages.RolesPage;


public class RolesSteps {

    RolesPage rolesPage;

    public RolesSteps(RolesPage rolesPage) {
        this.rolesPage = rolesPage;
    }

    public void createRole(Roles role) {
        rolesPage
                .addRole(role);
    }

    public void updateRole(Roles role) {
        rolesPage
                .addRole(role)
                .updateRole(role);
    }

    public void deleteRole(Roles role) {
        rolesPage
                .addRole(role)
                .deleteRole(role);
    }
}