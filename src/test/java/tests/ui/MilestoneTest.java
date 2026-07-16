package tests.ui;

import config.TestConfig;
import ui.dto.Milestone;
import ui.dto.Project;
import org.aeonbits.owner.ConfigFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.MilestoneFactory.getMilestone;
import static ui.dto.ProjectFactory.getProject;


public class MilestoneTest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Owner("Pavel")
    @Feature("Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create milestone")
    @Test
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
    @Test
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
    @Test()
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
    @Test
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
    @Test
    public void checkMilestoneDataTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .checkMilestoneData(project, milestone);
    }
}