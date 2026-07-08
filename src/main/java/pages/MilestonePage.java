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


    @Step("Открываем дашборд проектов")
    public MilestonePage openDashboard() {
        log.info("Открываем дашборд проектов");
        Selenide.open("/index.php?/dashboard/");

        return this;
    }

    @Step("Создаём проект '{project.name}'")
    public MilestonePage createProjectForMilestone(Project project) {
        log.info("Создаём проект '{}'", project.getName());
        $(ADD_PROJECT_BUTTON).shouldBe(visible).click();
        createProjectPage.isPageOpen();
        sleep(100);
        $(PROJECT_NAME_INPUT).setValue(project.getName());
        $x(PROJECT_DESCRIPTION_INPUT).click();
        $x(PROJECT_DESCRIPTION_INPUT).sendKeys(project.getDescription());
        $(CREATE_PROJECT_BUTTON).click();

        return this;
    }

    @Step("Создаём майлстон '{milestone.title}'")
    public MilestonePage createMilestone(Project project, Milestone milestone) {
        log.info("Заходим в меню заранее созданного проекта '{}'", project.getName());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        log.info("Создаём майлстон '{}'", milestone.getTitle());
        $(ADD_MILESTONE_BUTTON).click();
        createMilestonePage.isPageOpen();
        sleep(100);
        $(MILESTONE_TITLE_INPUT).setValue(milestone.getTitle());
        $(MILESTONE_DESCRIPTION_INPUT).sendKeys(milestone.getDescription());
        $(CREATE_MILESTONE_BUTTON).click();

        return this;
    }

    @Step("Удаляем проект '{0}'")
    public MilestonePage deleteProject(Project project) {
        log.info("Удаляем проект '{}'", project.getName());
        Selenide.open("/index.php?/admin/projects/overview");
        $x((String.format(DELETE_PROJECT_BUTTON, project.getName()))).click();
        $(DELETE_CHECKBOX_CONFIRM).click();
        $(DELETE_PROJECT_BUTTON_OK).click();

        return this;
    }

    @Step("Удаляем майлстоун '{milestone.title}' в проекте '{project.name}'")
    public MilestonePage deleteMilestone(Project project, Milestone milestone) {
        log.info("Удаляем майлстоун {} в проекте {}", project.getName(), milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $(deleteMilestoneButton(milestone)).click();
        $(DELETE_MILESTONE_BUTTON_OK).click();

        return this;
    }

    @Step("Проверяем, что проект '{0}' удалён")
    public MilestonePage isProjectDeleted(Project project) {
        log.info("Проверяем, что проект '{}' удалён", project.getName());
        Selenide.open("/index.php?/admin/projects/overview");
        $x((String.format(PROJECT_NAME_IN_TABLE, project.getName()))).shouldNot(exist);

        return this;
    }

    @Step("Проверяем, что майлстоун '{0}' удалён")
    public MilestonePage isMilestoneDeleted(Project project, Milestone milestone) {
        log.info("Проверяем, что майлстоун '{}' удалён", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x((String.format(MILESTONE_TITLE_FIELD_ON_MILESTONES_PAGE, milestone.getTitle()))).shouldNotBe(exist);

        return this;
    }

    @Step("Обновляем майлстоун '{milestone.title}' в проекте '{project.name}'")
    public MilestonePage updateMilestone(Project project, Milestone milestone) {
        String updatedTitle = milestone.getTitle() + "_updated";
        log.info("Обновляем майлстоун {} в проекте {}", project.getName(), milestone.getTitle());
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

    @Step("Проверяем, что майлстоун '{0}' был обновлен")
    public MilestonePage isMileStoneUpdated(Project project, Milestone milestone) {
        String updatedTitle = milestone.getTitle() + "_updated";
        log.info("Проверяем, что майлстоун '{}' был обновлен", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, updatedTitle)).click();
        $(MILESTONE_PAGE_TITLE_HEADER).shouldHave(exactText(updatedTitle));

        return this;
    }

    @Step("Завершаем майлстоун '{milestone.title}' в проекте '{project.name}'")
    public MilestonePage completeMilestone(Project project, Milestone milestone) {
        log.info("Завершаем майлстоун {} в проекте {}", project.getName(), milestone.getTitle());
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

    @Step("Проверяем, что майлстоун '{0}' был завершен")
    public MilestonePage isMilestoneCompleted(Project project, Milestone milestone) {
        log.info("Проверяем, что майлстоун '{}' был закончен", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $x(String.format(MILESTONE_TITLE_BUTTON_ON_MILESTONES_PAGE, milestone.getTitle())).click();
        $(MILESTONE_COMPLETED_MESSAGE).shouldHave(exactText("This milestone is completed. You can also close test runs & plans in this milestone to archive them permanently."));

        return this;
    }

    @Step("Проверяем данные майлстона '{0}' на странице Status")
    public MilestonePage checkMilestoneStatsOnStatusPage(Project project, Milestone milestone) {
        log.info("Проверяем статистику майлстона '{}' на странице Status ", milestone.getTitle());
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

    @Step("Проверяем данные майлстона '{0}' на странице Activity")
    public MilestonePage checkMilestoneStatsOnActivityPage(Milestone milestone) {
        log.info("Проверяем статистику майлстона '{}' на странице Activity ", milestone.getTitle());
        $(ACTIVITY_SIDE_BAR_BUTTON).click();
        ElementsCollection activityPageStats = $$(STATUS_AND_ACTIVITY_PAGE_STATS_TEXT).shouldHave(sizeGreaterThan(0));
        for (SelenideElement activityPageStat : activityPageStats) {
            activityPageStat.shouldHave(text("0% set to"));
        }

        return this;
    }

    @Step("Проверяем данные майлстона '{0}' на странице Progress")
    public MilestonePage checkMilestoneStatsOnProgressPage(Milestone milestone) {
        log.info("Проверяем статистику майлстона '{}' на странице Progress ", milestone.getTitle());
        $(PROGRESS_SIDE_BAR_BUTTON).click();
        $(ERROR_MESSAGE).shouldHave(text("No test runs available for displaying the progress of this milestone."));

        return this;
    }

    @Step("Проверяем данные майлстона '{0}' на странице Progress")
    public MilestonePage checkMilestoneStatsOnDefectsPage(Milestone milestone) {
        log.info("Проверяем статистику майлстона '{}' на странице Defects ", milestone.getTitle());
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