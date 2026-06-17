package tests.ui;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AdminPage;
import pages.CreateProjectPage;
import pages.LoginPage;
import pages.ProjectsPage;
import steps.LoginStep;

import java.util.HashMap;

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
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://tms34.testrail.io";
        Configuration.timeout = 30000;
        Configuration.clickViaJs = true;
        //Configuration.headless = true;
        //Configuration.assertionMode = AssertionMode.SOFT;
        Configuration.browserSize = "1920x1080";

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        //options.addArguments("--headless");
        Configuration.browserCapabilities = options;

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
