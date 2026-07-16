package ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.*;

import static com.codeborne.selenide.Selenide.executeJavaScript;


public class BasePage {

    protected static final Logger log = LogManager.getLogger(BasePage.class);

    protected static final String DASHBOARD_PATH = "/index.php?/dashboard";
    protected static final String ADMIN_PROJECTS_PATH = "/index.php?/admin/projects/overview";
    protected static final String ADMIN_USERS_PATH = "/index.php?/admin/users/overview";
    protected static final String SETTINGS_PATH = "/index.php?/mysettings";

    protected static final String ADD_PROJECT_BUTTON = "[data-testid='sidebarProjectsAddButton']";
    protected static final String PROJECT_NAME_INPUT = "[data-testid='addProjectNameInput']";
    protected static final String PROJECT_DESCRIPTION_INPUT =
            "[data-testid='addEditProjectAnnouncement'] .fr-element[contenteditable='true']";
    protected static final String CREATE_PROJECT_BUTTON = "[data-testid='addEditProjectAddButton']";
    protected static final String PROJECT_ROW = "//tr[.//a[normalize-space(text())='%s']]";
    protected static final String PROJECT_DELETE_BUTTON = PROJECT_ROW + "//div[@data-testid='projectDeleteButton']";

    protected static final String DELETE_CONFIRMATION_CHECKBOX =
            "[data-testid='caseFieldsTabDeleteDialogCheckbox'] label";
    protected static final String DELETE_DIALOG_CHECKBOX_INPUT =
            "//div[@id='deleteDialog']//input[@data-testid='deleteCheckBoxTestId']";
    protected static final String DELETE_CONFIRMATION_BUTTON =
            "[data-testid='caseFieldsTabDeleteDialogButtonOk']";
    protected static final String NAMED_ITEM_TEXT = "//span[@class='name' and text()='%s']";

    protected static final String CONTENT_HEADER_TITLE = "[data-testid='testCaseContentHeaderTitle']";
    protected static final String DESCRIPTION_EDITOR =
            "[data-testid='editSectionDescription'] .fr-element[contenteditable='true']";
    protected static final String SUCCESS_MESSAGE_BOX = "[data-testid='messageSuccessDivBox']";
    protected static final String USER_FULL_NAME_INPUT = "[data-testid=addEditUserName]";
    protected static final String USER_SAVE_BUTTON = "[data-testid=addEditUserAcceptButton]";

    protected CreateProjectPage createProjectPage() {
        return new CreateProjectPage();
    }

    protected CreateMilestonePage createMilestonePage() {
        return new CreateMilestonePage();
    }

    protected ProjectsPage projectsPage() {
        return new ProjectsPage();
    }

    protected TestCaseCreatePage testCaseCreatePage() {
        return new TestCaseCreatePage();
    }

    protected void triggerChange(SelenideElement element) {
        executeJavaScript("if(window.jQuery){jQuery(arguments[0]).trigger('change');}", element.toWebElement());
    }
}