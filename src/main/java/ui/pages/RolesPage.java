package ui.pages;

import com.codeborne.selenide.Selenide;
import ui.dto.Roles;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;


public class RolesPage extends BasePage {

    private static final String ROLES_HEADER_BUTTON = "[data-testid=roleTabNavigationUserRoles]";
    private static final String ADD_ROLE_BUTTON = "[data-testid=sidebarAddRolesButton]";
    private static final String ROLE_TITLE_INPUT_FIELD = "[data-testid=addEditRoleFormName]";
    private static final String CREATE_ROLE_BUTTON = "[data-testid=addEditRoleFormAddButton]";
    private static final String DELETE_ROLE_BUTTON = "//span[text()='%s']/ancestor::tr//div[@data-testid='roleTabDeleteIcon']";


    @Step("Add a new role")
    public RolesPage addRole(Roles role) {
        log.info("Add a new role '{}'", role.getTitle());
        Selenide.open(ADMIN_USERS_PATH);
        $(ROLES_HEADER_BUTTON).shouldBe(visible).click();
        $(ADD_ROLE_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(ROLE_TITLE_INPUT_FIELD).shouldBe(visible).setValue(role.getTitle());
        $(CREATE_ROLE_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_ADDED_THE_NEW_ROLE)).shouldBe(visible);
        $x(String.format(NAMED_ITEM_TEXT, role.getTitle())).shouldBe(visible);

        return this;
    }

    @Step("Update role")
    public RolesPage updateRole(Roles role) {
        String updatedRoleTitle = role.getTitle() + "_updated";
        log.info("Update role '{}'", role.getTitle());
        sleep(200);
        $x(String.format(NAMED_ITEM_TEXT, role.getTitle())).shouldBe(visible).click();
        $(ROLE_TITLE_INPUT_FIELD).shouldBe(visible).clear();
        sleep(200);
        $(ROLE_TITLE_INPUT_FIELD).shouldBe(visible).setValue(updatedRoleTitle);
        $(CREATE_ROLE_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_UPDATED_THE_ROLE)).shouldBe(visible);
        $x(String.format(NAMED_ITEM_TEXT, updatedRoleTitle)).shouldBe(visible);

        return this;
    }


    @Step("Delete role")
    public RolesPage deleteRole(Roles role) {
        log.info("Delete role '{}'", role.getTitle());
        sleep(200);
        $x(String.format(DELETE_ROLE_BUTTON, role.getTitle())).shouldBe(visible).click();
        sleep(200);
        $x(DELETE_DIALOG_CHECKBOX_INPUT).shouldBe(visible).click();
        sleep(200);
        $(DELETE_CONFIRMATION_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_DELETED_THE_ROLE)).shouldBe(visible);
        $x(String.format(NAMED_ITEM_TEXT, role.getTitle())).shouldNotBe(visible);

        return this;
    }
}
