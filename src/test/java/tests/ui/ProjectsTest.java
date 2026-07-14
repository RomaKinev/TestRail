package tests.ui;

import ui.dto.Project;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.ProjectFactory.getProject;
import static tests.ui.LoginTest.CONFIG;

public class ProjectsTest extends BaseUITest {

    @Owner("Roma")
    @Feature("Project Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new project")
    @Test(description = "Create a new project", groups = {"ui", "smoke"})
    public void createProjectTest() {
        Project project = getProject();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectStep.createProject(project);
    }

    @Owner("Roma")
    @Feature("Project Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create and then delete a project")
    @Test(description = "Create and then delete a project", groups = {"ui"})
    public void createAndDeleteProjectTest() {
        Project project = getProject();
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectStep.createProject(project);
        projectStep.deleteProject(project.getName());
    }

    @Owner("Roma")
    @Feature("Project Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit project name")
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
