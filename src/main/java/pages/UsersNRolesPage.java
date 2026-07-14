package pages;

import com.codeborne.selenide.Selenide;
import dto.User;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static dict.Elements.FORGET_THIS_USER;


public class UsersNRolesPage {

    private static final Logger log = LogManager.getLogger(UsersNRolesPage.class);

    private static final String ADD_USER_BUTTON = "[data-testid=sidebarAddUsersButton]";
    private static final String USER_FULL_NAME_INPUT = "[data-testid=addEditUserName]";
    private static final String USER_EMAIL_INPUT = "[data-testid=addEditUserEmail]";
    private static final String CREATE_USER_BUTTON = "[data-testid=addEditUserAcceptButton]";
    private static final String COLUMN_USER_TEXT_VALUE = "a[title='%s']";
    private static final String DELETE_CHECKBOX = "//div[@id='deleteDialog']//input[@data-testid='deleteCheckBoxTestId']";
    private static final String DELETE_DIALOGUE_BUTTON_OK = "[data-testid=caseFieldsTabDeleteDialogButtonOk]";
    private static final String SUCCESS_USER_DELETION_MESSAGE = "//div[@data-testid='messageSuccessDivBox' and contains(text(), 'Successfully forgot the user')]";


    @Step("Add a user")
    public UsersNRolesPage addUser(User user) {
        log.info("Create user '{}'", user.getFullName());
        Selenide.open("/index.php?/admin/users/overview");
        $(ADD_USER_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(USER_FULL_NAME_INPUT).shouldBe(visible).setValue(user.getFullName());
        $(USER_EMAIL_INPUT).shouldBe(visible).setValue(user.getEmail());
        $(CREATE_USER_BUTTON).shouldBe(visible).click();
        $(String.format(COLUMN_USER_TEXT_VALUE, user.getFullName())).shouldBe(visible);

        return this;
    }

    @Step("Update user data")
    public UsersNRolesPage updateUserInformation(User user) {
        String updatedUserFullName = user.getFullName() + "_updated";
        log.info("Update user '{}'", user.getFullName());
        $(String.format(COLUMN_USER_TEXT_VALUE, user.getFullName())).shouldBe(visible).click();
        sleep(200);
        $(USER_FULL_NAME_INPUT).shouldBe(visible).clear();
        $(USER_FULL_NAME_INPUT).shouldBe(visible).setValue(updatedUserFullName);
        $(CREATE_USER_BUTTON).shouldBe(visible).click();
        $(String.format(COLUMN_USER_TEXT_VALUE, updatedUserFullName)).shouldBe(visible);

        return this;
    }

    @Step("Deactivate user")
    public UsersNRolesPage deactivateUser(User user) {
        log.info("Deactivate user '{}'", user.getFullName());
        $(String.format(COLUMN_USER_TEXT_VALUE, user.getFullName())).shouldBe(visible).click();
        sleep(200);
        $(byText(FORGET_THIS_USER)).shouldBe(visible).shouldBe(enabled).click();
        sleep(200);
        $x(DELETE_CHECKBOX).shouldBe(visible).click();
        sleep(200);
        $(DELETE_DIALOGUE_BUTTON_OK).shouldBe(visible).shouldBe(enabled).click();
        $x(SUCCESS_USER_DELETION_MESSAGE).shouldBe(visible);
        Selenide.open("/index.php?/admin/users/overview");
        $x(String.format(COLUMN_USER_TEXT_VALUE, user.getFullName())).shouldNotBe(visible);

        return this;
    }
}