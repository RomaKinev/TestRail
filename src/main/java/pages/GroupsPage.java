package pages;

import com.codeborne.selenide.Selenide;
import dto.Group;
import io.qameta.allure.Step;
import org.apache.logging.log4j.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.*;


public class GroupsPage {

    private static final Logger log = LogManager.getLogger(GroupsPage.class);

    private static final String GROUPS_HEADER_BUTTON = "[id=navigation-user-groups]";
    private static final String ADD_GROUP_BUTTON = "[data-testid=groupsTabAddButton]";
    private static final String GROUP_TITLE_INPUT_FIELD = "[data-testid=addEditGroupFormName]";
    private static final String CREATE_GROUP_BUTTON = "[data-testid=addEditGroupFormSaveButton]";
    private static final String COLUMN_GROUP_TEXT_VALUE = "//span[@class='name' and text()='%s']";
    private static final String DELETE_GROUP_BUTTON = "//span[text()='%s']/ancestor::tr//div[@data-testid='groupsTabDeleteIcon']";
    private static final String DELETE_GROUP_CHECKBOX = "//div[@id='deleteDialog']//input[@data-testid='deleteCheckBoxTestId']";
    private static final String DELETE_GROUP_OK_BUTTON = "[data-testid=caseFieldsTabDeleteDialogButtonOk]";


    @Step("Add a new user group")
    public GroupsPage addGroup(Group group) {
        log.info("Create a new user group '{}'", group.getTitle());
        Selenide.open("/index.php?/admin/users/overview");
        $(GROUPS_HEADER_BUTTON).shouldBe(visible).click();
        $(ADD_GROUP_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(GROUP_TITLE_INPUT_FIELD).shouldBe(visible).setValue(group.getTitle());
        $(CREATE_GROUP_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_ADDED_THE_NEW_USER_GROUP)).shouldBe(visible);
        $x(String.format(COLUMN_GROUP_TEXT_VALUE, group.getTitle())).shouldBe(visible);

        return this;
    }

    @Step("Update user group")
    public GroupsPage updateGroup(Group group) {
        String updatedGroupTitle = group.getTitle() + "_updated";
        log.info("Update user group '{}'", group.getTitle());
        sleep(200);
        $x(String.format(COLUMN_GROUP_TEXT_VALUE, group.getTitle())).shouldBe(visible).click();
        $(GROUP_TITLE_INPUT_FIELD).shouldBe(visible).clear();
        sleep(200);
        $(GROUP_TITLE_INPUT_FIELD).shouldBe(visible).setValue(updatedGroupTitle);
        $(CREATE_GROUP_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_UPDATED_THE_USER_GROUP)).shouldBe(visible);
        $x(String.format(COLUMN_GROUP_TEXT_VALUE, updatedGroupTitle)).shouldBe(visible);

        return this;
    }


    @Step("Delete user group")
    public GroupsPage deleteGroup(Group group) {
        log.info("Delete user group '{}'", group.getTitle());
        sleep(200);
        $x(String.format(DELETE_GROUP_BUTTON, group.getTitle())).shouldBe(visible).click();
        sleep(200);
        $x(DELETE_GROUP_CHECKBOX).shouldBe(visible).click();
        sleep(200);
        $(DELETE_GROUP_OK_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(byText(SUCCESSFULLY_DELETED_THE_USER_GROUP)).shouldBe(visible);
        $x(String.format(COLUMN_GROUP_TEXT_VALUE, group.getTitle())).shouldNotBe(visible);

        return this;
    }
}