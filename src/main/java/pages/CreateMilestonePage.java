package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.ADD_MILESTONE;


public class CreateMilestonePage {

    private static final Logger log = LogManager.getLogger(CreateMilestonePage.class);

    @Step("Проверяем, что открыта форма создания майлстона")
    public void isPageOpen() {
        log.info("Проверяем, что открыта форма создания майлстона");
        $(byText(ADD_MILESTONE)).shouldBe(visible);
    }
}
