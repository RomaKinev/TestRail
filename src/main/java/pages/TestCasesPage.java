package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TestCasesPage {

    private static final Logger log = LogManager.getLogger(TestCasesPage.class);

    TestCaseCreatePage testCaseCreatePage = new TestCaseCreatePage();
    TestCasePage testCasePage = new TestCasePage();
    ProjectsPage projectsPage = new ProjectsPage();

    public static final String TEST_CASES_TITLE = "//span[@id='sectionName-42']";
    public static final String ADD_TEST_CASE = "[data-testid='sidebarCasesAdd']";
    public static final String ADD_SECTION = "[data-testid='addSectionInline']";
    public static final String TEST_CASE_NAME = "//span[text()='%s']";
    public static final String TEST_CASE_CHECKBOX = "//span[text()='%s']/ancestor::tr//input[@type='checkbox']";
    public static final String DELETE_TEST_CASE_BUTTON = "//span[text()='%s']/ancestor::tr//a[@class='deleteLink']";
    public static final String DELETE_FIRST_WINDOW = "[data-testid='casesDeletionDialog']";
    public static final String DELETE_PERMANENTLY_BUTTON = "[data-testid='casesDeletionDialog'] [data-testid='deleteCaseDialogActionSecondary']";
    public static final String DELETE_SECOND_WINDOW = "[data-testid='casesDeletionConfirmationDialog']";
    public static final String DELETE_CONFIRM_BUTTON = "[data-testid='casesDeletionConfirmationDialog'] [data-testid='deleteCaseDialogActionDefault']";


//    public TestCasesPage open() {

//    }

    @Step("Проверяем, что открыта страница тест-кейсов")
    public TestCasesPage isPageOpen() {
        log.info("Проверяем, что открыта страница тест-кейсов");
        $x(TEST_CASES_TITLE).shouldBe(visible);
        return this;
    }

    @Step("Добавляем тест-кейс")
    public TestCasePage addTestCase() {
        log.info("Добавляем тест-кейс");
        $(ADD_TEST_CASE).click();
        testCaseCreatePage.isPageOpen()
                .createTestCase("Test Case");
        return new TestCasePage();
    }

    @Step("Создаём и удаляем тест-кейс '{1}' в проекте '{0}'")
    public TestCasesPage addAndDeleteTestCase(String projectName, String testCaseName) {
        log.info("Создаём и удаляем тест-кейс '{}' в проекте '{}'", testCaseName, projectName);
        $(ADD_TEST_CASE).click();
        testCaseCreatePage.isPageOpen()
                .createTestCase("Test Case")
                .isCaseCreated();
        projectsPage.open()
                .openTestCasesByProject(projectName);
        $x(String.format(TEST_CASE_CHECKBOX, testCaseName)).click();
        $x(String.format(DELETE_TEST_CASE_BUTTON, testCaseName)).click();
        $(DELETE_FIRST_WINDOW).shouldBe(visible);
        $(DELETE_PERMANENTLY_BUTTON).click();
        $(DELETE_SECOND_WINDOW).shouldBe(visible);
        $(DELETE_CONFIRM_BUTTON).click();
        return this;
    }

    @Step("Проверяем, что тест-кейс '{0}' не отображается")
    public TestCasesPage isTestCaseNotVisible(String testCaseName) {
        log.info("Проверяем, что тест-кейс '{}' не отображается", testCaseName);
        $x(String.format(TEST_CASE_NAME, testCaseName)).shouldNot(visible);
        return this;
    }

    @Step("Открываем тест-кейс '{0}'")
    public TestCasePage openTestCase(String testCaseName) {
        log.info("Открываем тест-кейс '{}'", testCaseName);
        $x(String.format(TEST_CASE_NAME, testCaseName)).click();
        return new TestCasePage();
    }
}
