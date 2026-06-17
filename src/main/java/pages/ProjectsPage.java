package pages;

import com.codeborne.selenide.Selenide;
import dict.Elements;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.PROJECTS_PAGE;

public class ProjectsPage {

    CreateProjectPage createProjectPage = new CreateProjectPage();

    public final String ADMIN_BUTTON = "[data-testid='navigationUser']";
    public final String PROFILE_DROPDOWN = "[data-testid='userDropdown']";
    public final String LOGOUT_BUTTON = "[data-testid='logoutButton']";
    public final String ADD_PROJECT_BUTTON = "[data-testid='sidebarProjectsAddButton']";
    public final String PROJECT_NAME_INPUT = "[data-testid='addProjectNameInput']";
    public final String PROJECT_DESCRIPTION_INPUT = "[data-testid='addEditProjectAnnouncement'] .fr-element[contenteditable='true']";
    public final String CREATE_PROJECT_BUTTON = "[data-testid='addEditProjectAddButton']";

    public ProjectsPage isPageOpen() {
        $(byText(PROJECTS_PAGE)).shouldBe(visible);
        return this;
    }

    public ProjectsPage open() {
        Selenide.open("/index.php?/dashboard/");
        return this;
    }

    public ProjectsPage createProject(String projectName, String projectDescription) {
        $(ADD_PROJECT_BUTTON).click();
        createProjectPage.isPageOpen();
        $(PROJECT_NAME_INPUT).setValue(projectName);
        $(PROJECT_DESCRIPTION_INPUT).click();
        $(PROJECT_DESCRIPTION_INPUT).sendKeys(projectDescription);
        $(CREATE_PROJECT_BUTTON).click();
        return this;
    }

    public LoginPage logOut() {
        $(PROFILE_DROPDOWN).click();
        $(LOGOUT_BUTTON).click();
        return new LoginPage();
    }
}
