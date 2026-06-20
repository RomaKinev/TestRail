package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.PROJECTS_PAGE;

public class ProjectsPage {

    private static final Logger log = LogManager.getLogger(ProjectsPage.class);

    CreateProjectPage createProjectPage = new CreateProjectPage();

    public final String ADMIN_BUTTON = "[data-testid='navigationUser']";
    public final String PROFILE_DROPDOWN = "[data-testid='userDropdown']";
    public final String LOGOUT_BUTTON = "[data-testid='logoutButton']";
    public final String ADD_PROJECT_BUTTON = "[data-testid='sidebarProjectsAddButton']";
    public static final String PROJECT_NAME_INPUT = "[data-testid='addProjectNameInput']";
    public final String PROJECT_DESCRIPTION_INPUT = "[data-testid='addEditProjectAnnouncement'] .fr-element[contenteditable='true']";
    public static final String CREATE_PROJECT_BUTTON = "[data-testid='addEditProjectAddButton']";
    public final String TEST_CASES_PAGE_BUTTON = "//a[text()='%s']/ancestor::div[2]//a[text()='Test Cases']";
    public final String TEST_RUNS_BUTTON = "//a[text()='%s']/ancestor::div[2]//a[text()='Test Runs']";
    public final String REPORTS_BUTTON = "//a[text()='%s']/ancestor::div[2]//a[text()='Reports']";


    @Step("Проверяем, что открыта страница проектов")
    public ProjectsPage isPageOpen() {
        log.info("Проверяем, что открыта страница проектов");
        $(byText(PROJECTS_PAGE)).shouldBe(visible);
        return this;
    }

    @Step("Открываем дашборд проектов")
    public ProjectsPage open() {
        log.info("Открываем дашборд проектов");
        Selenide.open("/index.php?/dashboard/");
        return this;
    }

    @Step("Создаём проект '{0}'")
    public ProjectsPage createProject(String projectName, String projectDescription) {
        log.info("Создаём проект '{}'", projectName);
        $(ADD_PROJECT_BUTTON).click();
        createProjectPage.isPageOpen();
        $(PROJECT_NAME_INPUT).setValue(projectName);
        $(PROJECT_DESCRIPTION_INPUT).click();
        $(PROJECT_DESCRIPTION_INPUT).sendKeys(projectDescription);
        $(CREATE_PROJECT_BUTTON).click();
        return this;
    }

    @Step("Открываем Test Cases проекта '{0}'")
    public TestCasesPage openTestCasesByProject(String projectName) {
        log.info("Открываем Test Cases проекта '{}'", projectName);
        $x(String.format(TEST_CASES_PAGE_BUTTON, projectName)).click();
        return new TestCasesPage();
    }

    @Step("Выходим из аккаунта")
    public LoginPage logOut() {
        log.info("Выходим из аккаунта");
        $(PROFILE_DROPDOWN).click();
        $(LOGOUT_BUTTON).click();
        return new LoginPage();
    }
}
