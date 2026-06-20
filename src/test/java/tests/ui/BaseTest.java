package tests.ui;

import config.SelenideConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import steps.LoginStep;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {

    LoginPage loginPage;
    ProjectsPage projectsPage;
    AdminPage adminPage;
    LoginStep loginStep;
    CreateProjectPage createProjectPage;
    TestCasesPage testCasesPage;
    TestCaseCreatePage testCaseCreatePage;
    TestCasePage testCasePage;

    @BeforeMethod
    public void setUp() {
        SelenideConfig.configure();

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        adminPage = new AdminPage();
        loginStep = new LoginStep(loginPage);
        createProjectPage = new CreateProjectPage();
        testCasesPage = new TestCasesPage();
        testCaseCreatePage = new TestCaseCreatePage();
        testCasePage = new TestCasePage();
    }

    @AfterMethod
    public void tearDown() {
        if (hasWebDriverStarted()) {
            getWebDriver().quit();
        }
    }

}
