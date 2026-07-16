package tests.api;

import api.models.attachments.*;
import api_adapters.*;
import io.qameta.allure.*;
import listeners.RetryAnalyzer;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;

import static org.testng.Assert.*;


public class AttachmentAPITest extends BaseAPITest {

    private static final String PROJECT_CODE = "2";
    private static final String SECTION_DESCRIPTION = "API attachment tests section";
    private static final String CASE_TITLE = "API attachment test case";
    private static final String RUN_NAME = "API attachment test run";
    private static final File ATTACHMENT_FILE = new File("src/test/resources/attachment.png");

    private TestCaseRs testCase;
    private String caseAttachmentId;
    private String runAttachmentId;

    @Owner("Pavel")
    @Feature("Attachments")
    @Description("Verify an attachment can be added to a test case")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify an attachment can be added to a test case",
            description = "Verify an attachment can be added to a test case",
            groups = "api",
            priority = 1,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void addAttachmentToTestCase() {
        assertTrue(ATTACHMENT_FILE.isFile(), "Attachment fixture was not found: " + ATTACHMENT_FILE.getPath());
        testCase = TestCaseAdapter.createTestCaseWithSection(PROJECT_CODE, "API attachment section",
                SECTION_DESCRIPTION, CASE_TITLE);
        Integer caseId = testCase.getId();
        AttachmentUploadRs attachmentRs = AttachmentAdapter.addAttachmentToCase(caseId, ATTACHMENT_FILE);
        caseAttachmentId = attachmentRs.getAttachmentId();

        assertNotNull(caseAttachmentId, "Attachment ID was not returned after upload to test case.");
    }

    @Owner("Pavel")
    @Feature("Attachments")
    @Description("Verify an attachment can be added to a test run")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify an attachment can be added to a test run",
            description = "Verify an attachment can be added to a test run",
            groups = "api",
            priority = 2,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void addAttachmentToTestRun() {
        assertNotNull(testCase, "Test case must be created before creating a test run.");
        TestRunRs testRun = TestRunAdapter.createTestRunForCase(PROJECT_CODE, RUN_NAME, "API attachment test run",
                testCase);
        Integer runId = testRun.getId();
        AttachmentUploadRs attachmentRs = AttachmentAdapter.addAttachmentToRun(runId, ATTACHMENT_FILE);
        runAttachmentId = attachmentRs.getAttachmentId();

        assertNotNull(runAttachmentId, "Attachment ID was not returned after upload to test run.");
    }

    @Owner("Pavel")
    @Feature("Attachments")
    @Description("Verify a test case attachment can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a test case attachment can be retrieved",
            description = "Verify a test case attachment can be retrieved",
            groups = "api",
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void retrieveAttachmentFromTestCase() throws IOException {
        assertNotNull(caseAttachmentId, "Test case attachment must be created before retrieval.");
        byte[] downloadedAttachment = AttachmentAdapter.getAttachment(caseAttachmentId);

        assertEquals(downloadedAttachment, Files.readAllBytes(ATTACHMENT_FILE.toPath()),
                "Downloaded test case attachment content does not match the uploaded file.");
    }


    @Owner("Pavel")
    @Feature("Attachments")
    @Description("Verify a test run attachment can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a test run attachment can be retrieved",
            description = "Verify a test run attachment can be retrieved",
            groups = "api",
            priority = 4,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void retrieveAttachmentFromTestRun() throws IOException {
        assertNotNull(runAttachmentId, "Test run attachment must be created before retrieval.");
        byte[] downloadedAttachment = AttachmentAdapter.getAttachment(runAttachmentId);

        assertEquals(downloadedAttachment, Files.readAllBytes(ATTACHMENT_FILE.toPath()),
                "Downloaded test run attachment content does not match the uploaded file.");
    }

    @Owner("Pavel")
    @Feature("Attachments")
    @Description("Verify a test run attachment can be deleted")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a test run attachment can be deleted",
            description = "Verify a test run attachment can be deleted",
            groups = "api",
            priority = 5,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteAttachmentFromTestRun() {
        assertNotNull(runAttachmentId, "Test run attachment must be created before deletion.");
        String deletedAttachmentId = runAttachmentId;
        AttachmentAdapter.deleteAttachment(deletedAttachmentId);
        int statusCode = AttachmentAdapter.getAttachmentStatusCode(deletedAttachmentId);

        assertEquals(statusCode, 400, "Deleted test run attachment is still retrievable. Status code: " + statusCode);
    }

    @Owner("Pavel")
    @Feature("Attachments")
    @Description("Verify a test case attachment can be deleted")
    @Severity(SeverityLevel.NORMAL)
    @Test(
            testName = "Verify a test case attachment can be deleted",
            description = "Verify a test case attachment can be deleted",
            groups = "api",
            priority = 6,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void deleteAttachmentFromTestCase() {
        assertNotNull(caseAttachmentId, "Test case attachment must be created before deletion.");
        String deletedAttachmentId = caseAttachmentId;
        AttachmentAdapter.deleteAttachment(deletedAttachmentId);
        int statusCode = AttachmentAdapter.getAttachmentStatusCode(deletedAttachmentId);

        assertEquals(statusCode, 400, "Deleted test case attachment is still retrievable. Status code: " + statusCode);
    }
}