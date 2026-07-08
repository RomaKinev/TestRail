package tests.ui;

import dto.Project;
import org.testng.annotations.Test;

import static dto.ProjectFactory.getProject;
import static tests.ui.LoginUITest.CONFIG;

public class ProjectsUITest extends BaseUITest {

    @Test(description = "Create a new project", groups = {"ui", "smoke"})
    public void createProjectTest() {
        Project project = getProject();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectStep.createProject(project);
    }

    @Test(description = "Create and then delete a project", groups = {"ui"})
    public void createAndDeleteProjectTest() {
        Project project = getProject();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectStep.createProject(project);
        projectStep.deleteProject(project.getName());
    }

    @Test(description = "Edit project name", groups = {"ui"})
    public void editProjectNameTest() {
        Project project = getProject();
        String newName = "Edited_" + project.getName();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectStep.createProject(project);
        adminPage.editProjectName(project.getName(), newName)
                .isProjectNameChanged(project.getName(), newName)
                .deleteProject(newName);
    }
}
