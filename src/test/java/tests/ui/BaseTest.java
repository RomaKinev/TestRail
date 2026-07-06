package tests.ui;

import com.codeborne.selenide.Selenide;
import config.SelenideConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import pages.ProjectsPage;
import steps.LoginStep;
import steps.MilestoneStep;
import steps.ProjectStep;

public class BaseTest {

    LoginPage loginPage;
    ProjectsPage projectsPage;
    AdminPage adminPage;
    LoginStep loginStep;
    ProjectStep projectStep;
    CreateProjectPage createProjectPage;
    TestCasesPage testCasesPage;
    TestCaseCreatePage testCaseCreatePage;
    TestCasePage testCasePage;
    TestSuitesPage testSuitesPage;
    MilestonePage milestonePage;
    MilestoneStep milestoneStep;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        SelenideConfig.configure();

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        adminPage = new AdminPage();
        loginStep = new LoginStep(loginPage);
        projectStep = new ProjectStep();
        createProjectPage = new CreateProjectPage();
        testCasesPage = new TestCasesPage();
        testCaseCreatePage = new TestCaseCreatePage();
        testCasePage = new TestCasePage();
        testSuitesPage = new TestSuitesPage();
        milestonePage = new MilestonePage();
        milestoneStep = new MilestoneStep(projectsPage, milestonePage, adminPage);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
