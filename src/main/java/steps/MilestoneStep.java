package steps;

import dto.Milestone;
import dto.Project;
import lombok.extern.log4j.Log4j2;
import pages.*;

@Log4j2
public class MilestoneStep {

    ProjectsPage projectsPage;
    MilestonePage milestonePage;
    AdminPage adminPage;

    public MilestoneStep(ProjectsPage projectsPage, MilestonePage milestonePage, AdminPage adminPage) {
        this.projectsPage = projectsPage;
        this.milestonePage = milestonePage;
        this.adminPage = adminPage;
    }

    public void createMilestone(Project project, Milestone milestone) {
        milestonePage
                .openDashboard()
                .createProjectForMilestone(project)
                .createMilestone(project, milestone)
                .deleteProject(project)
                .isProjectDeleted(project);
    }

    public void deleteMilestone(Project project, Milestone milestone) {
        milestonePage
                .openDashboard()
                .createProjectForMilestone(project)
                .createMilestone(project, milestone)
                .deleteMilestone(project, milestone)
                .isMilestoneDeleted(project, milestone);
    }
}
