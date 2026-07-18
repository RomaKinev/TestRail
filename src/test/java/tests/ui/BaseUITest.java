package tests.ui;

import com.codeborne.selenide.Selenide;
import config.*;
import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.*;
import ui.pages.*;
import ui.steps.*;


@Listeners({AllureTestNg.class, TestListener.class})
public class BaseUITest {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    LoginPage loginPage;
    ProjectsPage projectsPage;
    AdminPage adminPage;
    LoginStep loginStep;
    ProjectStep projectStep;
    TestCasesPage testCasesPage;
    TestCasePage testCasePage;
    TestCaseStep testCaseStep;
    TestSuitesPage testSuitesPage;
    TestSuiteStep testSuiteStep;
    TestRunPage testRunPage;
    ReportsPage reportsPage;
    TestRunStep testRunStep;
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
        testCasesPage = new TestCasesPage();
        testCasePage = new TestCasePage();
        testCaseStep = new TestCaseStep(projectsPage, testCasesPage, testCasePage);
        testSuitesPage = new TestSuitesPage();
        testSuiteStep = new TestSuiteStep(projectsPage, testSuitesPage);
        testRunPage = new TestRunPage();
        reportsPage = new ReportsPage();
        testRunStep = new TestRunStep(projectsPage, testSuitesPage, testRunPage, reportsPage);
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