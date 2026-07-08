package pages;

import dto.TestCase;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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
    public static final String EDIT_TEST_CASE_BUTTON = "//span[text()='%s']/ancestor::tr//a[@class='editLink']";
    public static final String EDIT_TEST_CASE_BUTTON1 = "[data-testid='testCaseEditButton']";
    public static final String DELETE_TEST_CASE_BUTTON = "//span[text()='%s']/ancestor::tr//a[@class='deleteLink']";
    public static final String DELETE_FIRST_WINDOW = "[data-testid='casesDeletionDialog']";
    public static final String DELETE_PERMANENTLY_BUTTON = "[data-testid='casesDeletionDialog'] [data-testid='deleteCaseDialogActionSecondary']";
    public static final String DELETE_SECOND_WINDOW = "[data-testid='casesDeletionConfirmationDialog']";
    public static final String DELETE_CONFIRM_BUTTON = "[data-testid='casesDeletionConfirmationDialog'] [data-testid='deleteCaseDialogActionDefault']";

    // --- Проверка нахождения кейса в секции ---
    public static final String CASE_IN_SECTION =
            "//div[contains(@class,'grid-container')]" +
                    "[.//span[contains(@class,'group-toggle-title') and normalize-space(text())='%s']]" +
                    "//span[@data-testid='sectionCaseTitle' and normalize-space(text())='%s']";
    public static final String CASE_IN_DEFAULT_SECTION =
            "//span[@data-testid='sectionCaseTitle' and normalize-space(text())='%s']";
    public static final String ANY_CASE_TITLE = "[data-testid='sectionCaseTitle']";


//    public TestCasesPage open() {

//    }

    @Step("Проверяем, что открыта страница тест-кейсов")
    public TestCasesPage isPageOpen() {
        log.info("Проверяем, что открыта страница тест-кейсов");
        $x(TEST_CASES_TITLE).shouldBe(visible);
        return this;
    }

    @Step("Добавляем тест-кейс '{0.title}'")
    public TestCasePage addTestCase(TestCase testCase) {
        log.info("Добавляем тест-кейс '{}'", testCase.getTitle());
        $(ADD_TEST_CASE).click();
        testCaseCreatePage.isPageOpen()
                .createTestCase(testCase);
        return new TestCasePage();
    }

    @Step("Создаём и удаляем тест-кейс '{1.title}' в проекте '{0}'")
    public TestCasesPage addAndDeleteTestCase(String projectName, TestCase testCase) {
        String testCaseName = testCase.getTitle();
        log.info("Создаём и удаляем тест-кейс '{}' в проекте '{}'", testCaseName, projectName);
        $(ADD_TEST_CASE).click();
        testCaseCreatePage.isPageOpen()
                .createTestCase(testCase)
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

    @Step("Берём название первого тест-кейса из списка")
    public String getFirstCaseTitle() {
        String title = $$(ANY_CASE_TITLE).first().shouldBe(visible).getText().trim();
        log.info("Первый тест-кейс в списке: '{}'", title);
        return title;
    }

    @Step("Открываем тест-кейс '{0}'")
    public TestCasePage openTestCase(String testCaseName) {
        log.info("Открываем тест-кейс '{}'", testCaseName);
        $x(String.format(TEST_CASE_NAME, testCaseName)).click();
        return new TestCasePage();
    }

    @Step("Редактируем тест-кейс")
    public TestCasePage editTestCase(String testCaseName, String newTestCaseTitle) {
        log.info("Редактируем тест-кейс '{}'", testCaseName);
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen()
                .editTestCase(newTestCaseTitle)
                .isCaseUpdated();
        return new TestCasePage();
    }

    @Step("Смена приоритета тест-кейс")
    public TestCasePage changePriorityTestCase(String newPriority) {
        log.info("Смена приоритета тест-кейс на '{}'", newPriority);
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen()
                .changePriority(newPriority)
                .isCaseUpdated();
        return new TestCasePage();
    }

    @Step("Перемещаем тест-кейс в секцию '{0}'")
    public TestCasePage changeSectionTestCase(String newSection) {
        log.info("Перемещаем тест-кейс в секцию '{}'", newSection);
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen()
                .changeSection(newSection)
                .isCaseUpdated();
        return new TestCasePage();
    }

    @Step("Проверяем, что кейс '{0}' есть в секции '{1}'")
    public TestCasesPage verifyCaseExistsInSection(String caseTitle, String sectionName) {
        log.info("Проверяем, что кейс '{}' есть в секции '{}'", caseTitle, sectionName);
        $x(String.format(CASE_IN_SECTION, sectionName, caseTitle)).shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что кейс '{0}' есть в дефолтной секции")
    public TestCasesPage verifyCaseExistsInDefaultSection(String caseTitle) {
        log.info("Проверяем, что кейс '{}' есть в дефолтной секции", caseTitle);
        $x(String.format(CASE_IN_DEFAULT_SECTION, caseTitle)).shouldBe(visible);
        return this;
    }

    @Step("Меняем приоритет на отличный от текущего")
    public String changePriorityToDifferent() {
        $(EDIT_TEST_CASE_BUTTON1).click();
        testCaseCreatePage.isPageOpen();
        String current = testCaseCreatePage.getCurrentPriority();
        String newPriority = current.equalsIgnoreCase("High") ? "Low" : "High";
        testCaseCreatePage.changePriority(newPriority).isCaseUpdated();
        return newPriority;
    }
}
