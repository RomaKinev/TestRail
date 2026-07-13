package tests.ui;

import com.codeborne.selenide.Selenide;
import config.SelenideConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import pages.ProjectsPage;
import steps.*;

public class BaseUITest {

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
    TestRunPage testRunPage;
    RunStep runStep;
    MilestonePage milestonePage;
    MilestoneStep milestoneStep;
    UsersNRolesPage usersNRolesPage;
    UsersNRolesStep usersNRolesStep;
    GroupsPage groupsPage;
    GroupsStep groupStep;
    RolesPage rolesPage;
    RolesSteps rolesSteps;
    SettingsPage settingsPage;
    SettingsStep settingsStep;

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
        testRunPage = new TestRunPage();
        runStep = new RunStep(projectsPage, testSuitesPage, testRunPage);
        milestonePage = new MilestonePage();
        milestoneStep = new MilestoneStep(projectsPage, milestonePage, adminPage);
        usersNRolesPage = new UsersNRolesPage();
        usersNRolesStep = new UsersNRolesStep(usersNRolesPage);
        groupsPage = new GroupsPage();
        groupStep = new GroupsStep(groupsPage);
        rolesPage = new RolesPage();
        rolesSteps = new RolesSteps(rolesPage);
        settingsPage = new SettingsPage();
        settingsStep = new SettingsStep(settingsPage);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}