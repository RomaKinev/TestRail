package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.ADD_PROJECT_TITLE;

public class CreateProjectPage {

    private static final Logger log = LogManager.getLogger(CreateProjectPage.class);

    @Step("Verify the project creation form is open")
    public void isPageOpen() {
        log.info("Verify the project creation form is open");
        $(byText(ADD_PROJECT_TITLE)).shouldBe(visible);
    }
}
