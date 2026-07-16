package ui.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;


public class AdminPage extends BasePage {

    public final String EDIT_PROJECT_BUTTON = PROJECT_ROW + "//div[@data-testid='projectEditButton']";

    @Step("Open the admin projects page")
    public AdminPage openAdminPage() {
        log.info("Open the admin projects page");
        Selenide.open(ADMIN_PROJECTS_PATH);

        return this;
    }

    @Step("Verify the admin page is open")
    public AdminPage isPageOpen() {
        log.info("Verify the admin page is open");
        $(byText(ADMIN_PAGE)).shouldBe(visible);

        return this;
    }

    @Step("Verify project '{0}' is created")
    public AdminPage isProjectCreated(String projectName) {
        log.info("Verify project '{}' is created", projectName);
        $(byText(SUCCESS_MESSAGE_AFTER_CREATE_PROJECT)).shouldBe(visible);
        $(By.xpath(String.format(PROJECT_ROW, projectName))).shouldBe(visible);

        return this;
    }

    @Step("Verify project '{0}' is displayed")
    public AdminPage isProjectVisible(String projectName) {
        log.info("Verify project '{}' is displayed", projectName);
        $(By.xpath(String.format(PROJECT_ROW, projectName))).shouldBe(visible);

        return this;
    }

    @Step("Delete project '{0}'")
    public AdminPage deleteProject(String projectName) {
        log.info("Delete project '{}'", projectName);
        $(By.xpath(String.format(PROJECT_DELETE_BUTTON, projectName))).click();
        $(DELETE_CONFIRMATION_CHECKBOX).click();
        $(DELETE_CONFIRMATION_BUTTON).click();

        return this;
    }

    @Step("Verify project '{0}' is deleted")
    public AdminPage isProjectDeleted(String projectName) {
        log.info("Verify project '{}' is deleted", projectName);
        $(By.xpath(String.format(PROJECT_ROW, projectName))).shouldNot(exist);

        return this;
    }

    @Step("Rename project '{0}' to '{1}'")
    public AdminPage editProjectName(String projectName, String newName) {
        log.info("Rename project '{}' to '{}'", projectName, newName);
        $(By.xpath(String.format(EDIT_PROJECT_BUTTON, projectName))).click();
        $(PROJECT_NAME_INPUT).shouldBe(visible);
        $(PROJECT_NAME_INPUT).clear();
        $(PROJECT_NAME_INPUT).setValue(newName);
        $(CREATE_PROJECT_BUTTON).click();

        return this;
    }

    @Step("Verify project '{0}' is renamed to '{1}'")
    public AdminPage isProjectNameChanged(String projectName, String newName) {
        log.info("Verify project '{}' is renamed to '{}'", projectName, newName);
        $(byText(SUCCESS_MESSAGE_AFTER_EDIT_PROJECT)).shouldBe(visible);

        return this;
    }
}