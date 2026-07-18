package tests.api;

import api.models.projects.*;
import api_adapters.ProjectAdapter;
import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ProjectAPITest extends BaseAPITest {

    private static final String PROJECT_NAME = "API test project";
    private static final Integer SINGLE_SUITE_MODE = 1;
    private Integer createdProjectId;

    @Owner("Roma")
    @Feature("Projects API")
    @Description("Verify the projects list can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify the projects list can be retrieved",
            description = "Verify the projects list can be retrieved",
            groups = "api",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void getProjects() {
        ProjectsRs projectsRs = ProjectAdapter.getProjects();

        assertFalse(projectsRs.getProjects().isEmpty(), "The projects list is empty.");
    }

    @Owner("Roma")
    @Feature("Projects API")
    @Description("Verify a project can be created")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a project can be created",
            description = "Verify a project can be created",
            groups = "api",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void createProject() {
        ProjectRq projectRq = ProjectRq.builder()
                .name(PROJECT_NAME)
                .suite_mode(SINGLE_SUITE_MODE)
                .build();
        ProjectRs projectRs = ProjectAdapter.createProject(projectRq);
        createdProjectId = projectRs.getId();

        assertNotNull(createdProjectId, "The created project ID was not returned.");
        assertEquals(projectRs.getName(), PROJECT_NAME, "The created project name does not match.");
    }

    @Owner("Roma")
    @Feature("Projects API")
    @Description("Verify the created project can be deleted")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify the created project can be deleted",
            description = "Verify the created project can be deleted",
            groups = "api",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteProject() {
        ProjectAdapter.deleteProjectIfCreated(createdProjectId);
    }
}
