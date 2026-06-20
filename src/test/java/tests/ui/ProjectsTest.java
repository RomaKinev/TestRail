package tests.ui;

import config.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

public class ProjectsTest extends BaseTest{

    private static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
    private static final String PROJECT_NAME = "TestProject";
    private static final String PROJECT_DESCRIPTION = "TestProjectDescription";

    @Test
    public void createProjectTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .createProject(PROJECT_NAME, PROJECT_DESCRIPTION);
        adminPage.isProjectCreated(PROJECT_NAME);
    }

    @Test
    public void createAndDeleteProjectTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .createProject(PROJECT_NAME, PROJECT_DESCRIPTION);
        adminPage.isProjectCreated(PROJECT_NAME)
                .deleteProject(PROJECT_NAME)
                .isProjectDeleted(PROJECT_NAME);
    }

    @Test
    public void editProjectNameTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        projectsPage.isPageOpen()
                .createProject(PROJECT_NAME, PROJECT_DESCRIPTION);
        adminPage.isProjectCreated(PROJECT_NAME)
                .editProjectName(PROJECT_NAME, "NewProjectName1")
                .isProjectNameChanged(PROJECT_NAME, "NewProjectName1")
                .deleteProject("NewProjectName1");
    }
}
