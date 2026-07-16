package ui.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.ADD_MILESTONE;


public class CreateMilestonePage extends BasePage {

    @Step("Verify the milestone creation form is open")
    public void isPageOpen() {
        log.info("Verify the milestone creation form is open");
        $(byText(ADD_MILESTONE)).shouldBe(visible);
    }
}