package tests.ui;

import config.SelenideConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AdminPage;
import pages.CreateProjectPage;
import pages.LoginPage;
import pages.ProjectsPage;
import steps.LoginStep;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {

    LoginPage loginPage;
    ProjectsPage projectsPage;
    AdminPage adminPage;
    LoginStep loginStep;
    CreateProjectPage createProjectPage;

    @BeforeMethod
    public void setUp() {
        SelenideConfig.configure();

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        adminPage = new AdminPage();
        loginStep = new LoginStep(loginPage);
        createProjectPage = new CreateProjectPage();
    }

    @AfterMethod
    public void tearDown() {
        if (hasWebDriverStarted()) {
            getWebDriver().quit();
        }
    }

}
