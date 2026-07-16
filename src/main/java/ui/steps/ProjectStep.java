package ui.steps;

import ui.dto.Project;
import lombok.extern.log4j.Log4j2;
import ui.pages.*;


@Log4j2
public class ProjectStep {

    ProjectsPage projectsPage = new ProjectsPage();
    AdminPage adminPage = new AdminPage();

    public void createProject(Project project) {
        log.info("Creating new project with data: {}", project.getName());
        projectsPage.isPageOpen()
                .createProject(project);
        adminPage.isProjectCreated(project.getName());
    }

    public void deleteProject(String projectName) {
        log.info("Deleting project: {}", projectName);
        adminPage.openAdminPage()
                .deleteProject(projectName)
                .isProjectDeleted(projectName);
    }
}