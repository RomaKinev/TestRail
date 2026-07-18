package tests.api;

import api.models.attachments.*;
import api.models.cases.TestCasesRs;
import api.models.projects.*;
import api_adapters.*;
import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.*;

import static org.testng.Assert.*;


public class TestCaseAPITest extends BaseAPITest {

    private static final String PROJECT_NAME = "API case tests project";
    private static final String SECTION_NAME = "API case tests section";
    private static final String SECTION_DESCRIPTION = "Section for test case API tests";
    private static final String CASE_TITLE = "API test case";
    private static final String UPDATED_CASE_TITLE = "API test case_updated";
    private static final Integer SINGLE_SUITE_MODE = 1;
    private ProjectRs project;
    private TestCaseRs testCase;

    @BeforeClass(alwaysRun = true)
    public void createProject() {
        project = ProjectAdapter.createProject(ProjectRq.builder()
                .name(PROJECT_NAME)
                .suite_mode(SINGLE_SUITE_MODE)
                .build());
    }

    @AfterClass(alwaysRun = true)
    public void deleteProject() {
        if (project != null) {
            ProjectAdapter.deleteProjectIfCreated(project.getId());
        }
    }

    private String projectId() {
        return String.valueOf(project.getId());
    }

    @Owner("Roma")
    @Feature("Test Cases API")
    @Description("Verify a test case can be created")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a test case can be created",
            description = "Verify a test case can be created",
            groups = "api",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void createTestCase() {
        testCase = TestCaseAdapter.createTestCaseWithSection(projectId(), SECTION_NAME,
                SECTION_DESCRIPTION, CASE_TITLE);

        assertNotNull(testCase.getId(), "The created test case ID was not returned.");
        assertEquals(testCase.getTitle(), CASE_TITLE, "The created test case title does not match.");
    }

    @Owner("Roma")
    @Feature("Test Cases API")
    @Description("Verify project test cases can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify project test cases can be retrieved",
            description = "Verify project test cases can be retrieved",
            groups = "api",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void getTestCases() {
        assertNotNull(testCase, "Test case must be created before retrieving the list.");
        TestCasesRs casesRs = TestCaseAdapter.getCases(projectId());

        assertTrue(casesRs.getCases().stream().anyMatch(c -> c.getId().equals(testCase.getId())),
                "The created test case was not found in the project cases list.");
    }

    @Owner("Roma")
    @Feature("Test Cases API")
    @Description("Verify a test case title can be updated")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a test case title can be updated",
            description = "Verify a test case title can be updated",
            groups = "api",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void updateTestCase() {
        assertNotNull(testCase, "Test case must be created before updating.");
        TestCaseRq updateRq = TestCaseRq.builder()
                .title(UPDATED_CASE_TITLE)
                .build();
        TestCaseRs updatedCase = TestCaseAdapter.updateCase(testCase.getId(), updateRq);

        assertEquals(updatedCase.getTitle(), UPDATED_CASE_TITLE, "The test case title was not updated.");
    }

    @Owner("Roma")
    @Feature("Test Cases API")
    @Description("Verify the created section with the test case can be deleted")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify the created section with the test case can be deleted",
            description = "Verify the created section with the test case can be deleted",
            groups = "api",
            priority = 4,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteTestCaseSection() {
        if (testCase != null) {
            SectionAdapter.deleteSectionIfCreated(testCase.getSectionId());
        }
    }
}
