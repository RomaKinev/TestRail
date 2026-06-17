package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static dict.Elements.ADD_PROJECT_TITLE;

public class CreateProjectPage {

    public void isPageOpen() {
        $(byText(ADD_PROJECT_TITLE)).shouldBe(visible);
    }
}
