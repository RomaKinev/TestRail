package pages;

import com.codeborne.selenide.Selenide;
import dto.Group;
import dto.Roles;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static dict.Elements.*;


public class RolesPage {

    private static final Logger log = LogManager.getLogger(RolesPage.class);

    private static final String ROLES_HEADER_BUTTON = "[data-testid=roleTabNavigationUserRoles]";
    private static final String ADD_ROLE_BUTTON = "[data-testid=sidebarAddRolesButton]";
    private static final String ROLE_TITLE_INPUT_FIELD = "[data-testid=addEditRoleFormName]";
    private static final String CREATE_ROLE_BUTTON = "[data-testid=addEditRoleFormAddButton]";
    private static final String COLUMN_ROLE_TEXT_VALUE = "//span[@class='name' and text()='%s']";
    private static final String DELETE_ROLE_BUTTON = "//span[text()='%s']/ancestor::tr//div[@data-testid='roleTabDeleteIcon']";
    private static final String DELETE_ROLE_CHECKBOX = "//div[@id='deleteDialog']//input[@data-testid='deleteCheckBoxTestId']";
    private static final String DELETE_ROLE_OK_BUTTON = "[data-testid=caseFieldsTabDeleteDialogButtonOk]";


    @Step("Добавляем новую роль")
    public RolesPage addRole(Roles role) {
        log.info("Добавляем новую роль'{}'", role.getTitle());
        Selenide.open("/index.php?/admin/users/overview");
        $(ROLES_HEADER_BUTTON).shouldBe(visible).click();
        $(ADD_ROLE_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(ROLE_TITLE_INPUT_FIELD).shouldBe(visible).setValue(role.getTitle());
        $(CREATE_ROLE_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_ADDED_THE_NEW_ROLE)).shouldBe(visible);
        $x(String.format(COLUMN_ROLE_TEXT_VALUE, role.getTitle())).shouldBe(visible);

        return this;
    }

    @Step("Обновляем роль")
    public RolesPage updateRole(Roles role) {
        String updatedRoleTitle = role.getTitle() + "_updated";
        log.info("Обновляем роль'{}'", role.getTitle());
        sleep(200);
        $x(String.format(COLUMN_ROLE_TEXT_VALUE, role.getTitle())).shouldBe(visible).click();
        $(ROLE_TITLE_INPUT_FIELD).shouldBe(visible).clear();
        sleep(200);
        $(ROLE_TITLE_INPUT_FIELD).shouldBe(visible).setValue(updatedRoleTitle);
        $(CREATE_ROLE_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_UPDATED_THE_ROLE)).shouldBe(visible);
        $x(String.format(COLUMN_ROLE_TEXT_VALUE, updatedRoleTitle)).shouldBe(visible);

        return this;
    }


    @Step("Удаляем роль")
    public RolesPage deleteRole(Roles role) {
        log.info("Удаляем роль'{}'", role.getTitle());
        sleep(200);
        $x(String.format(DELETE_ROLE_BUTTON, role.getTitle())).shouldBe(visible).click();
        sleep(200);
        $x(DELETE_ROLE_CHECKBOX).shouldBe(visible).click();
        sleep(200);
        $(DELETE_ROLE_OK_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_DELETED_THE_ROLE)).shouldBe(visible);
        $x(String.format(COLUMN_ROLE_TEXT_VALUE, role.getTitle())).shouldNotBe(visible);

        return this;
    }
}