package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.Milestone;
import dto.Project;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
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


    @Step("Открываем дашборд проектов")
    public MilestonePage openDashboard() {
        log.info("Открываем дашборд проектов");
        Selenide.open("/index.php?/dashboard/");
        return this;
    }

    @Step("Создаём проект '{project.name}'")
    public MilestonePage createProjectForMilestone(Project project) {
        log.info("Создаём проект '{}'", project.getName());
        $(ADD_PROJECT_BUTTON).click();
        createProjectPage.isPageOpen();
        sleep(100);
        $(PROJECT_NAME_INPUT).setValue(project.getName());
        $(PROJECT_DESCRIPTION_INPUT).click();
        $(PROJECT_DESCRIPTION_INPUT).sendKeys(project.getDescription());
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
        $(By.xpath(String.format(DELETE_PROJECT_BUTTON, project.getName()))).click();
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
        $(By.xpath(String.format(PROJECT_NAME_IN_TABLE, project.getName()))).shouldNot(exist);

        return this;
    }

    @Step("Проверяем, что майлстоун '{0}' удалён")
    public MilestonePage isMilestoneDeleted(Project project, Milestone milestone) {
        log.info("Проверяем, что майлстоун '{}' удалён", milestone.getTitle());
        Selenide.open("/index.php?/dashboard");
        $(projectMilestonesButton(project)).click();
        $(By.xpath(String.format(MILESTONE_TITLE_FIELD_ON_MILESTONES_PAGE, milestone.getTitle()))).shouldNotBe(exist);

        return this;
    }

    public static SelenideElement projectMilestonesButton(Project project) {
        return $x(String.format("//div[contains(@class, 'flex-projects-row') and .//a[text()='%s']]//a[text()='Milestones']", project.getName()));
    }

    public static SelenideElement deleteMilestoneButton(Milestone milestone) {
        return $x(String.format("//div[contains(@class, 'flex-milestones-row') and .//text()='%s']//a[contains(@title, 'Delete this milestone')]", milestone.getTitle()));
    }
}