package pages;

import com.codeborne.selenide.Selenide;
import dto.User;
import io.qameta.allure.Step;
import org.apache.logging.log4j.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static dict.Elements.SUCCESSFULLY_SAVED_YOUR_SETTINGS;
import static dict.Elements.SUCCESSFULLY_SAVED_YOUR_SETTINGS_GERMAN;


public class SettingsPage {

    private static final Logger log = LogManager.getLogger(SettingsPage.class);

    private static final String FULL_NAME_INPUT_FIELD = "[data-testid=addEditUserName]";
    private static final String SAVE_CONFIGURATION_BUTTON = "[data-testid=addEditUserAcceptButton]";
    private static final String LANGUAGE_SELECT = "[data-testid=addEditUserLanguage]";
    private static final String COLOR_SCHEME_SELECT = "[data-testid=addEditUserTheme]";


    @Step("Меняем имя пользователя")
    public SettingsPage changeUsersName(User user) {
        log.info("Меняем имя пользовател на '{}'", user.getFullName());
        Selenide.open("/index.php?/mysettings");
        $(FULL_NAME_INPUT_FIELD).clear();
        sleep(200);
        $(FULL_NAME_INPUT_FIELD).shouldBe(visible).setValue(user.getFullName());
        sleep(200);
        $(SAVE_CONFIGURATION_BUTTON).shouldBe(visible).click();
        $(byText(SUCCESSFULLY_SAVED_YOUR_SETTINGS)).shouldBe(visible);
        $(FULL_NAME_INPUT_FIELD).shouldHave(exactValue(user.getFullName()));

        return this;
    }

    @Step("Меняем язык Testrail c английского на немецкий")
    public SettingsPage changeTestrailUILanguage() {
        log.info("Меняем язык с английского на немецкий");
        Selenide.open("/index.php?/mysettings");
        sleep(200);
        $(LANGUAGE_SELECT).selectOption("German");
        sleep(200);
        $(SAVE_CONFIGURATION_BUTTON).shouldBe(visible).click();
        $(byText(SUCCESSFULLY_SAVED_YOUR_SETTINGS_GERMAN)).shouldBe(visible);


        return this;
    }

    @Step("Меняем язык Testrail c немецкого на английский")
    public SettingsPage revertTestrailUILanguage() {
        log.info("Меняем язык с немецкий на английский");
        Selenide.open("/index.php?/mysettings");
        sleep(200);
        $(LANGUAGE_SELECT).selectOption("Englisch");
        sleep(200);
        $(SAVE_CONFIGURATION_BUTTON).shouldBe(visible).click();
        $(byText(SUCCESSFULLY_SAVED_YOUR_SETTINGS)).shouldBe(visible);


        return this;
    }

    @Step("Тему Testrail")
    public SettingsPage changeTestrailColorScheme() {
        log.info("Меняем тему Testrail с светлой на темную");
        Selenide.open("/index.php?/mysettings");
        sleep(200);
        $(COLOR_SCHEME_SELECT).selectOption("Dark");
        sleep(200);
        $(SAVE_CONFIGURATION_BUTTON).shouldBe(visible).click();
        $(byText(SUCCESSFULLY_SAVED_YOUR_SETTINGS)).shouldBe(visible);
        $(COLOR_SCHEME_SELECT).getSelectedOption().shouldHave(exactText("Dark"));

        return this;
    }

    @Step("Тему Testrail")
    public SettingsPage revertTestrailColorScheme() {
        log.info("Меняем тему Testrail с темной на светлую");
        Selenide.open("/index.php?/mysettings");
        sleep(200);
        $(COLOR_SCHEME_SELECT).selectOption("Light");
        sleep(200);
        $(SAVE_CONFIGURATION_BUTTON).shouldBe(visible).click();
        $(byText(SUCCESSFULLY_SAVED_YOUR_SETTINGS)).shouldBe(visible);
        $(COLOR_SCHEME_SELECT).getSelectedOption().shouldHave(exactText("Light"));

        return this;
    }

}
