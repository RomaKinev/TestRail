package ui.pages;

import com.codeborne.selenide.Selenide;
import ui.dto.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.FORGET_THIS_USER;


public class UsersNRolesPage extends BasePage {

    private static final String ADD_USER_BUTTON = "[data-testid=sidebarAddUsersButton]";
    private static final String USER_EMAIL_INPUT = "[data-testid=addEditUserEmail]";
    private static final String COLUMN_USER_TEXT_VALUE = "a[title='%s']";
    private static final String SUCCESS_USER_DELETION_MESSAGE = "//div[@data-testid='messageSuccessDivBox' and contains(text(), 'Successfully forgot the user')]";


    @Step("Add a user")
    public UsersNRolesPage addUser(User user) {
        log.info("Create user '{}'", user.getFullName());
        Selenide.open(ADMIN_USERS_PATH);
        $(ADD_USER_BUTTON).shouldBe(visible).click();
        sleep(200);
        $(USER_FULL_NAME_INPUT).shouldBe(visible).setValue(user.getFullName());
        $(USER_EMAIL_INPUT).shouldBe(visible).setValue(user.getEmail());
        $(USER_SAVE_BUTTON).shouldBe(visible).click();
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
        $(USER_SAVE_BUTTON).shouldBe(visible).click();
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
        $x(DELETE_DIALOG_CHECKBOX_INPUT).shouldBe(visible).click();
        sleep(200);
        $(DELETE_CONFIRMATION_BUTTON).shouldBe(visible).shouldBe(enabled).click();
        $x(SUCCESS_USER_DELETION_MESSAGE).shouldBe(visible);
        Selenide.open(ADMIN_USERS_PATH);
        $x(String.format(COLUMN_USER_TEXT_VALUE, user.getFullName())).shouldNotBe(visible);

        return this;
    }
}
