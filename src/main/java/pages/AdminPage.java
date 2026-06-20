package pages;

import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.*;
import static pages.ProjectsPage.CREATE_PROJECT_BUTTON;
import static pages.ProjectsPage.PROJECT_NAME_INPUT;

public class AdminPage {

    private static final Logger log = LogManager.getLogger(AdminPage.class);

    public static final String DELETE_PROJECT_BUTTON =
            "//tr[.//a[normalize-space(text())='%s']]" +
                    "//div[@data-testid='projectDeleteButton']";
    public final String DELETE_CHECKBOX_CONFIRM = "[data-testid='caseFieldsTabDeleteDialogCheckbox'] label";
    public final String DELETE_PROJECT_BUTTON_OK = "[data-testid='caseFieldsTabDeleteDialogButtonOk']";
    public final String PROJECT_NAME_IN_TABLE = "//tr[.//a[normalize-space(text())='%s']]";
    public final String EDIT_PROJECT_BUTTON = "//tr[.//a[normalize-space(text())='%s']]" +
            "//div[@data-testid='projectEditButton']";

    public AdminPage openAdminPage() {
        log.info("Открываем админ-страницу проектов");
        Selenide.open("index.php?/admin/projects/overview");
        return this;
    }

    public AdminPage isPageOpen() {
        log.info("Проверяем, что открыта админ-страница");
        $(byText(ADMIN_PAGE)).shouldBe(visible);
        return this;
    }

    public AdminPage isProjectCreated(String projectName) {
        log.info("Проверяем, что проект '{}' создан", projectName);
        $(byText(SUCCESS_MESSAGE_AFTER_CREATE_PROJECT)).shouldBe(visible);
        $(By.xpath(String.format(PROJECT_NAME_IN_TABLE, projectName))).shouldBe(visible);
        return this;
    }

    public AdminPage isProjectVisible(String projectName) {
        log.info("Проверяем, что проект '{}' отображается", projectName);
        $(By.xpath(String.format(PROJECT_NAME_IN_TABLE, projectName))).shouldBe(visible);
        return this;
    }

    public AdminPage deleteProject(String projectName) {
        log.info("Удаляем проект '{}'", projectName);
        $(By.xpath(String.format(DELETE_PROJECT_BUTTON, projectName))).click();
        $(DELETE_CHECKBOX_CONFIRM).click();
        $(DELETE_PROJECT_BUTTON_OK).click();
        return this;
    }

    public AdminPage isProjectDeleted(String projectName) {
        log.info("Проверяем, что проект '{}' удалён", projectName);
        $(By.xpath(String.format(PROJECT_NAME_IN_TABLE, projectName))).shouldNot(exist);
        return this;
    }

    public AdminPage editProjectName(String projectName, String newName) {
        log.info("Переименовываем проект '{}' в '{}'", projectName, newName);
        $(By.xpath(String.format(EDIT_PROJECT_BUTTON, projectName))).click();
        $(byText(EDIT_PROJECT_TITLE)).shouldBe(visible);
        $(PROJECT_NAME_INPUT).setValue(newName);
        $(CREATE_PROJECT_BUTTON).click();
        return this;
    }

    public AdminPage isProjectNameChanged(String projectName, String newName) {
        log.info("Проверяем, что проект '{}' переименован в '{}'", projectName, newName);
        $(byText(SUCCESS_MESSAGE_AFTER_EDIT_PROJECT)).shouldBe(visible);
        return this;
    }
}
