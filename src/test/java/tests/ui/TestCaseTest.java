package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.TestCaseFactory.getTestCase;


public class TestCaseTest extends BaseUITest {

    private static final String PROJECT = "Test";
    private static final String TARGET_SECTION = "section1";
    private static final String PRECONDITIONS = "Autotest precondition";

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new test case")
    @Test(description = "Create a new test case", groups = {"ui", "smoke"})
    public void testCaseCreationTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.createTestCase(PROJECT, getTestCase());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create and then delete a test case")
    @Test(description = "Create and then delete a test case", groups = {"ui"})
    public void testCaseCreationAndDeletionTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.createAndDeleteTestCase(PROJECT, getTestCase());
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open an existing test case")
    @Test(description = "Open an existing test case", groups = {"ui"})
    public void openTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.openFirstCase(PROJECT);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit test case title via edit form")
    @Test(description = "Edit test case title via edit form", groups = {"ui"})
    public void editTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.editFirstCaseTitle(PROJECT);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Change test case priority")
    @Test(description = "Change test case priority", groups = {"ui"})
    public void changePriorityInTestCaseTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.changeFirstCasePriority(PROJECT);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Move test case to another section via edit form")
    @Test(description = "Move test case to another section via edit form", groups = {"ui"})
    public void moveTestCaseToSectionTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.moveFirstCaseToSection(PROJECT, TARGET_SECTION);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fill a custom field (Preconditions) of a test case")
    @Test(description = "Fill a custom field of a test case", groups = {"ui"})
    public void fillCustomFieldTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.fillFirstCaseCustomField(PROJECT, PRECONDITIONS);
    }

    @Owner("Roma")
    @Feature("Test Cases")
    @Severity(SeverityLevel.NORMAL)
    @Description("Priority change is reflected in case history")
    @Test(description = "Priority change is reflected in case history", groups = {"ui"})
    public void priorityChangeInHistoryTest() {
        loginStep.auth(CONFIG.email(), CONFIG.password());
        testCaseStep.changeFirstCasePriorityAndCheckHistory(PROJECT);
    }
}
