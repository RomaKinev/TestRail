package tests.ui;

import listeners.RetryAnalyzer;
import ui.dto.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ui.dto.MilestoneFactory.getMilestone;
import static ui.dto.ProjectFactory.getProject;


public class MilestoneTest extends BaseUITest {

    @Owner("Pavel")
    @Feature("Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create milestone")
    @Test(
            testName = "Verify milestone can be created",
            description = "Verify milestone can be created",
            groups = "ui",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void createMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .createMilestone(project, milestone);
    }

    @Owner("Pavel")
    @Feature("Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete milestone")
    @Test(
            testName = "Verify milestone can be deleted",
            description = "Verify milestone can be deleted",
            groups = "ui",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .deleteMilestone(project, milestone);
    }

    @Owner("Pavel")
    @Feature("Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Update milestone")
    @Test(
            testName = "Verify milestone can be updated",
            description = "Verify milestone can be updated",
            groups = "ui",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void updateMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .updateMilestone(project, milestone);
    }

    @Owner("Pavel")
    @Feature("Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Complete milestone")
    @Test(
            testName = "Verify milestone can be completed",
            description = "Verify milestone can be completed",
            groups = "ui",
            priority = 4,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void completeMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .completeMilestone(project, milestone);
    }

    @Owner("Pavel")
    @Feature("Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Check milestone data")
    @Test(
            testName = "Verify milestone data is displayed",
            description = "Verify milestone data is displayed",
            groups = "ui",
            priority = 5,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void checkMilestoneDataTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .checkMilestoneData(project, milestone);
    }
}