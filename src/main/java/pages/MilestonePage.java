package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.Milestone;
import dto.Project;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;


public class MilestonePage {

    private static final Logger log = LogManager.getLogger(MilestonePage.class);

    CreateProjectPage createProjectPage = new CreateProjectPage();
    CreateMilestonePage createMilestonePage = new CreateMilestonePage();

    public final String ADD_PROJECT_BUTTON = "[data-testid='sidebarProjectsAddButton']";
    public static final String PROJECT_NAME_INPUT = "[data-testid='addProjectNameInput']";
    public final String PROJECT_DESCRIPTION_INPUT = "[data-testid='addEditProjectAnnouncement'] .fr-element[contenteditable='true']";
    public static final String CREATE_PROJECT_BUTTON = "[data-testid='addEditProjectAddButton']";
    public final String PROJECT_NAME_IN_TABLE = "//tr[.//a[normalize-space(text())='%s']]";
    public static final String DELETE_PROJECT_BUTTON =
            "//tr[.//a[normalize-space(text())='%s']]" +
                    "//div[@data-testid='projectDeleteButton']";
    public final String DELETE_PROJECT_BUTTON_OK = "[data-testid='caseFieldsTabDeleteDialogButtonOk']";
    public final String DELETE_CHECKBOX_CONFIRM = "[data-testid='caseFieldsTabDeleteDialogCheckbox'] label";
    public static final String ADD_MILESTONE_BUTTON = "[data-testid='navigationMilestonesAdd']";
    public static final String MILESTONE_TITLE_INPUT = "[data-testid='addEditMilestoneName']";
    public final String MILESTONE_DESCRIPTION_INPUT = "[data-testid='editSectionDescription'] .fr-element[contenteditable='true']";
    public static final String CREATE_MILESTONE_BUTTON = "[data-testid='milestoneButtonOk']";
    public static final String MILESTONE_TITLE_FIELD_ON_MILESTONES_PAGE = "//div[contains(@class, 'flex-milestones-row') and .//text()='%s']";
    public static final String DELETE_MILESTONE_BUTTON_OK = "[data-testid='caseFieldsTabDeleteDialogButtonOk']";
    public static final String MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE = "//div['completed']//a[text()='%s']";
    public static final String EDIT_MILESTONE_BUTTON = ".button-edit";
    public static final String MILESTONE_PAGE_TITLE_HEADER = "[data-testid='testCaseContentHeaderTitle']";
    public static final String COMPLETE_MILESTONE_BUTTON = "[data-testid='addEditMilestoneIsCompleted']";
    public static final String MILESTONE_COMPLETED_MESSAGE = "[data-testid='messageHelp']";
    public static final String STATUS_AND_ACTIVITY_PAGE_STATS_TEXT = ".chart-legend-description";
    public static final String STATUS_PAGE_CHART_PIE_STATS = ".chart-pie-column-percent";
    public static final String ACTIVITY_SIDE_BAR_BUTTON = "[data-testid=navigateToMilestoneActivityButton]";
    public static final String PROGRESS_SIDE_BAR_BUTTON = "[data-testid=navigateToMilestoneProgressButton]";
    public static final String DEFECTS_SIDE_BAR_BUTTON = "[data-testid=navigateToMilestoneDefectsButton]";
    public static final String ERROR_MESSAGE = "[data-testid=messageErrorTestid]";


    @Step("Open the projects dashboard")
    public MilestonePage openDashboard() {
        log.info("Open the projects dashboard");
        Selenide.open("/index.php?/dashboard/");

        return this;
    }

    @Step("Create project '{project.name}'")
    public MilestonePage createProjectForMilestone(Project project) {
        log.info("Create project '{}'", project.getName());
        $(ADD_PROJECT_BUTTON).shouldBe(visible).click();
        createProjectPage.isPageOpen();
        sleep(100);
        $(PROJECT_NAME_INPUT).setValue(project.getName());
        $x(PROJECT_DESCRIPTION_INPUT).click();
        $x(PROJECT_DESCRIPTION_INPUT).sendKeys(project.getDescription());
        $(CREATE_PROJECT_BUTTON).click();

        return this;
    }

    @Step("Create milestone '{milestone.title}'")
    public MilestonePage createMilestone(Project project, Milestone milestone) {
        log.info("Open the menu of the pre-created project '{}'", project.getName());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        log.info("Create milestone '{}'", milestone.getTitle());
        $(ADD_MILESTONE_BUTTON).click();
        createMilestonePage.isPageOpen();
        sleep(100);
        $(MILESTONE_TITLE_INPUT).setValue(milestone.getTitle());
        $(MILESTONE_DESCRIPTION_INPUT).sendKeys(milestone.getDescription());
        $(CREATE_MILESTONE_BUTTON).click();

        return this;
    }

    @Step("Delete project '{0}'")
    public MilestonePage deleteProject(Project project) {
        log.info("Delete project '{}'", project.getName());
        Selenide.open("/index.php?/admin/projects/overview");
        $x((String.format(DELETE_PROJECT_BUTTON, project.getName()))).click();
        $(DELETE_CHECKBOX_CONFIRM).click();
        $(DELETE_PROJECT_BUTTON_OK).click();

        return this;
    }

    @Step("Delete milestone '{milestone.title}' in project '{project.name}'")
    public MilestonePage deleteMilestone(Project project, Milestone milestone) {
        log.info("Delete milestone {} in project {}", project.getName(), milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $(deleteMilestoneButton(milestone)).click();
        $(DELETE_MILESTONE_BUTTON_OK).click();

        return this;
    }

    @Step("Verify project '{0}' is deleted")
    public MilestonePage isProjectDeleted(Project project) {
        log.info("Verify project '{}' is deleted", project.getName());
        Selenide.open("/index.php?/admin/projects/overview");
        $x((String.format(PROJECT_NAME_IN_TABLE, project.getName()))).shouldNot(exist);

        return this;
    }

    @Step("Verify milestone '{0}' was deleted")
    public MilestonePage isMilestoneDeleted(Project project, Milestone milestone) {
        log.info("Verify milestone '{}' was deleted", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x((String.format(MILESTONE_TITLE_FIELD_ON_MILESTONES_PAGE, milestone.getTitle()))).shouldNotBe(exist);

        return this;
    }

    @Step("Update milestone '{milestone.title}' in project '{project.name}'")
    public MilestonePage updateMilestone(Project project, Milestone milestone) {
        String updatedTitle = milestone.getTitle() + "_updated";
        log.info("Update milestone {} in project {}", project.getName(), milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, milestone.getTitle())).click();
        $(MILESTONE_PAGE_TITLE_HEADER).shouldBe(visible).shouldHave(text(milestone.getTitle()));
        $(EDIT_MILESTONE_BUTTON).shouldBe(visible);
        $(EDIT_MILESTONE_BUTTON).click();
        sleep(200);
        $(MILESTONE_TITLE_INPUT).setValue(updatedTitle);
        $(CREATE_MILESTONE_BUTTON).click();

        return this;
    }

    @Step("Verify milestone '{0}' was updated")
    public MilestonePage isMileStoneUpdated(Project project, Milestone milestone) {
        String updatedTitle = milestone.getTitle() + "_updated";
        log.info("Verify milestone '{}' was updated", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, updatedTitle)).click();
        $(MILESTONE_PAGE_TITLE_HEADER).shouldHave(exactText(updatedTitle));

        return this;
    }

    @Step("Complete milestone '{milestone.title}' in project '{project.name}'")
    public MilestonePage completeMilestone(Project project, Milestone milestone) {
        log.info("Complete milestone {} in project {}", project.getName(), milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, milestone.getTitle())).click();
        sleep(100);
        $(EDIT_MILESTONE_BUTTON).click();
        sleep(100);
        $(COMPLETE_MILESTONE_BUTTON).click();
        $(CREATE_MILESTONE_BUTTON).click();

        return this;
    }

    @Step("Verify milestone '{0}' was completed")
    public MilestonePage isMilestoneCompleted(Project project, Milestone milestone) {
        log.info("Verify milestone '{}' was completed", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, milestone.getTitle())).click();
        $(MILESTONE_COMPLETED_MESSAGE).shouldHave(exactText("This milestone is completed. You can also close test runs & plans in this milestone to archive them permanently."));

        return this;
    }

    @Step("Verify milestone '{0}' data on the Status page")
    public MilestonePage checkMilestoneStatsOnStatusPage(Project project, Milestone milestone) {
        log.info("Verify milestone '{}' stats on the Status page ", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, milestone.getTitle())).click();
        ElementsCollection statusPageStats = $$(STATUS_AND_ACTIVITY_PAGE_STATS_TEXT).shouldHave(sizeGreaterThan(0));
        for (SelenideElement statusPageStat : statusPageStats) {
            statusPageStat.shouldHave(text("0% set to"));
        }
        $(STATUS_PAGE_CHART_PIE_STATS).shouldHave(text("0% passed 0 / 0 untested (0%)."));

        return this;
    }

    @Step("Verify milestone '{0}' data on the Activity page")
    public MilestonePage checkMilestoneStatsOnActivityPage(Milestone milestone) {
        log.info("Verify milestone '{}' stats on the Activity page ", milestone.getTitle());
        $(ACTIVITY_SIDE_BAR_BUTTON).click();
        ElementsCollection activityPageStats = $$(STATUS_AND_ACTIVITY_PAGE_STATS_TEXT).shouldHave(sizeGreaterThan(0));
        for (SelenideElement activityPageStat : activityPageStats) {
            activityPageStat.shouldHave(text("0% set to"));
        }

        return this;
    }

    @Step("Verify milestone '{0}' data on the Progress page")
    public MilestonePage checkMilestoneStatsOnProgressPage(Milestone milestone) {
        log.info("Verify milestone '{}' stats on the Progress page ", milestone.getTitle());
        $(PROGRESS_SIDE_BAR_BUTTON).click();
        $(ERROR_MESSAGE).shouldHave(text("No test runs available for displaying the progress of this milestone."));

        return this;
    }

    @Step("Verify milestone '{0}' data on the Progress page")
    public MilestonePage checkMilestoneStatsOnDefectsPage(Milestone milestone) {
        log.info("Verify milestone '{}' stats on the Defects page ", milestone.getTitle());
        $(DEFECTS_SIDE_BAR_BUTTON).click();
        $(ERROR_MESSAGE).shouldHave(text("No test runs available for displaying the defects of this milestone."));

        return this;
    }

    public static SelenideElement projectMilestonesButton(Project project) {
        return $x(String.format("//div[contains(@class, 'flex-projects-row') " +
                        "and .//a[text()='%s']]//a[text()='Milestones']",
                project.getName()));
    }

    public static SelenideElement deleteMilestoneButton(Milestone milestone) {
        return $x(String.format("//div[contains(@class, 'flex-milestones-row') " +
                        "and .//text()='%s']//a[contains(@title, 'Delete this milestone')]",
                milestone.getTitle()));
    }
}