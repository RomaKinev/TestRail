package tests.ui;

import api.models.projects.*;
import api.models.sections.*;
import api_adapters.*;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.annotations.*;

import static ui.dto.TestCaseFactory.getTestCase;


public class TestCaseTest extends BaseUITest {

    private static final String CASES_SECTION = "SectionCases";
    private static final String BASE_CASE_TITLE = "case";
    private static final String TARGET_SECTION = "section1";
    private static final String PRECONDITIONS = "Autotest precondition";
    private static final Integer SINGLE_SUITE_MODE = 1;

    private ProjectRs project;

    @BeforeClass(alwaysRun = true)
    public void prepareProject() {
        project = ProjectAdapter.createProject(ProjectRq.builder()
                .name("UI Test Cases " + new Faker().number().digits(5))
                .suite_mode(SINGLE_SUITE_MODE)
                .build());
        String projectId = String.valueOf(project.getId());
        TestCaseAdapter.createTestCaseWithSection(projectId, CASES_SECTION,
                "Section for UI test case tests", BASE_CASE_TITLE);
        SectionAdapter.createSection(SectionRq.builder()
                .name(TARGET_SECTION)
                .description("Move target section")
                .build(), projectId);
    }

    @AfterClass(alwaysRun = true)
    public void deleteProject() {
        if (project != null) {
            ProjectAdapter.deleteProjectIfCreated(project.getId());
        }
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new test case")
    @Test(description = "Create a new test case", groups = {"ui", "smoke"})
    public void testCaseCreationTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.createTestCase(project.getName(), getTestCase());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create and then delete a test case")
    @Test(description = "Create and then delete a test case", groups = {"ui"})
    public void testCaseCreationAndDeletionTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.createAndDeleteTestCase(project.getName(), getTestCase());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open an existing test case")
    @Test(description = "Open an existing test case", groups = {"ui"})
    public void openTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.openFirstCase(project.getName());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit test case title via edit form")
    @Test(description = "Edit test case title via edit form", groups = {"ui"})
    public void editTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.editFirstCaseTitle(project.getName());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Change test case priority")
    @Test(description = "Change test case priority", groups = {"ui"})
    public void changePriorityInTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.changeFirstCasePriority(project.getName());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Move test case to another section via edit form")
    @Test(description = "Move test case to another section via edit form", groups = {"ui"})
    public void moveTestCaseToSectionTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.moveFirstCaseToSection(project.getName(), TARGET_SECTION);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fill a custom field (Preconditions) of a test case")
    @Test(description = "Fill a custom field of a test case", groups = {"ui"})
    public void fillCustomFieldTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.fillFirstCaseCustomField(project.getName(), PRECONDITIONS);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Priority change is reflected in case history")
    @Test(description = "Priority change is reflected in case history", groups = {"ui"})
    public void priorityChangeInHistoryTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.changeFirstCasePriorityAndCheckHistory(project.getName());
    }
}
