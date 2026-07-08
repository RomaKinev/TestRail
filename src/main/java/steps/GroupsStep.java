package steps;

import dto.Group;
import pages.GroupsPage;


public class GroupsStep {

    GroupsPage groupsPage;

    public GroupsStep(GroupsPage groupsPage) {
        this.groupsPage = groupsPage;
    }

    public void createGroup(Group group) {
        groupsPage
                .addGroup(group)
                .deleteGroup(group);
    }

    public void updateGroup(Group group) {
        groupsPage
                .addGroup(group)
                .updateGroup(group);
    }

    public void deleteGroup(Group group) {
        groupsPage
                .addGroup(group)
                .deleteGroup(group);
    }
}