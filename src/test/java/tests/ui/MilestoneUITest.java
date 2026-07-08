package tests.ui;

import config.TestConfig;
import dto.Milestone;
import dto.Project;
import org.aeonbits.owner.ConfigFactory;

import org.testng.annotations.Test;

import static dto.MilestoneFactory.getMilestone;
import static dto.ProjectFactory.getProject;


public class MilestoneUITest extends BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @Test
    public void createMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .createMilestone(project, milestone);
    }

    @Test
    public void deleteMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .deleteMilestone(project, milestone);
    }

    @Test()
    public void updateMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .updateMilestone(project, milestone);
    }

    @Test
    public void completeMilestoneTest() {
        Project project = getProject();
        Milestone milestone = getMilestone();

        loginStep
                .auth(CONFIG.email(), CONFIG.password());
        milestoneStep
                .completeMilestone(project, milestone);
    }

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