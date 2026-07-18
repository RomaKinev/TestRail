package ui.pages;

import com.codeborne.selenide.Selenide;
import ui.dto.Project;
import io.qameta.allure.Step;
import org.apache.logging.log4j.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;


public class ProjectsPage extends BasePage {

    public final String PROFILE_DROPDOWN = "[data-testid='userDropdown']";
    public final String LOGOUT_BUTTON = "[data-testid='logoutButton']";
    public static final String SUITE_MODE_MULTIPLE = "[data-testid='addEditProjectSuiteModeMulti']";
    public final String TEST_CASES_PAGE_BUTTON = "//a[text()=\"%s\"]/ancestor::div[2]//a[text()='Test Cases']";
    public static final String PROJECT_LINK = "//a[normalize-space(text())=\"%s\"]";
    public static final String NAVIGATE_TO_SUITES = "[data-testid='navigateToSuitesButton']";
    public static final String NAVIGATE_TO_RUNS = "#navigation-runs-dropdown";

    @Step("Verify the projects page is open")
    public ProjectsPage isPageOpen() {
        log.info("Verify the projects page is open");
        $(byText(PROJECTS_PAGE)).shouldBe(visible);

        return this;
    }

    @Step("Open the projects dashboard")
    public ProjectsPage open() {
        log.info("Open the projects dashboard");
        Selenide.open(DASHBOARD_PATH);

        return this;
    }

    @Step("Create project '{project.name}'")
    public ProjectsPage createProject(Project project) {
        log.info("Create project '{}'", project.getName());
        $(ADD_PROJECT_BUTTON).click();
        createProjectPage().isPageOpen();
        sleep(2000);
        $(PROJECT_NAME_INPUT).setValue(project.getName());
        $(PROJECT_DESCRIPTION_INPUT).click();
        $(PROJECT_DESCRIPTION_INPUT).sendKeys(project.getDescription());
        $(CREATE_PROJECT_BUTTON).click();

        return this;
    }

    @Step("Create project '{project.name}' with multiple suites support")
    public ProjectsPage createProjectWithSuites(Project project) {
        log.info("Create project '{}' in multiple suites mode", project.getName());
        $(ADD_PROJECT_BUTTON).click();
        createProjectPage().isPageOpen();
        sleep(2000);
        $(PROJECT_NAME_INPUT).setValue(project.getName());
        $(PROJECT_DESCRIPTION_INPUT).click();
        $(PROJECT_DESCRIPTION_INPUT).sendKeys(project.getDescription());
        $(SUITE_MODE_MULTIPLE).click();
        $(CREATE_PROJECT_BUTTON).click();
        $(byText(SUCCESS_MESSAGE_AFTER_CREATE_PROJECT)).shouldBe(visible);

        return this;
    }

    @Step("Open Test Cases of project '{0}'")
    public TestCasesPage openTestCasesByProject(String projectName) {
        log.info("Open Test Cases of project '{}'", projectName);
        $x(String.format(TEST_CASES_PAGE_BUTTON, projectName)).click();

        return new TestCasesPage();
    }

    @Step("Open project '{0}'")
    public ProjectsPage openProject(String projectName) {
        log.info("Open project '{}'", projectName);
        $$x(String.format(PROJECT_LINK, projectName)).findBy(visible).click();

        return this;
    }

    @Step("Go to Test Suites & Cases section")
    public TestSuitesPage goToTestSuites() {
        log.info("Go to Test Suites & Cases section");
        $(NAVIGATE_TO_SUITES).click();

        return new TestSuitesPage();
    }

    @Step("Go to Test Runs & Results section")
    public TestRunsPage goToTestRuns() {
        log.info("Go to Test Runs & Results section");
        $(NAVIGATE_TO_RUNS).click();

        return new TestRunsPage();
    }

    @Step("Log out of the account")
    public LoginPage logOut() {
        log.info("Log out of the account");
        $(PROFILE_DROPDOWN).click();
        $(LOGOUT_BUTTON).click();

        return new LoginPage();
    }
}
