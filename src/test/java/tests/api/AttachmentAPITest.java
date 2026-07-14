package tests.api;

import api.models.attachments.*;
import api_adapters.*;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.*;


public class AttachmentAPITest {

    private static final String PROJECT_CODE = "2";
    private static final String SECTION_DESCRIPTION = "API attachment tests section";
    private static final String CASE_TITLE = "API attachment test case";
    private static final String RUN_NAME = "API attachment test run";
    private static final String ATTACHMENT_CONTENT = "TestRail API attachment test file";

    private File attachment;
    private Integer sectionId;
    private Integer caseId;
    private Integer runId;
    private String attachmentId;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        Path attachmentPath = Files.createTempFile("testrail-api-attachment-", ".txt");
        Files.writeString(attachmentPath, ATTACHMENT_CONTENT);
        attachment = attachmentPath.toFile();

        TestCaseRs testCase = TestCaseAdapter.createTestCaseWithSection(PROJECT_CODE, "API attachment section",
                SECTION_DESCRIPTION, CASE_TITLE);
        TestRunRs testRun = TestRunAdapter.createTestRunForCase(PROJECT_CODE, RUN_NAME, "API attachment test run",
                testCase);
        sectionId = testCase.getCreatedSectionId();
        caseId = testCase.getId();
        runId = testRun.getId();
    }

    @Owner("Pavel")
    @Test(groups = {"api"})
    public void addAttachmentToTestCase() {
        AttachmentUploadRs attachmentRs = AttachmentAdapter.addAttachmentToCase(caseId, attachment);
        attachmentId = attachmentRs.getAttachmentId();

        assertNotNull(attachmentId, "Attachment ID was not returned after upload to test case.");
    }

    @Owner("Pavel")
    @Test(groups = {"api"})
    public void addAttachmentToTestRun() {
        AttachmentUploadRs attachmentRs = AttachmentAdapter.addAttachmentToRun(runId, attachment);
        attachmentId = attachmentRs.getAttachmentId();

        assertNotNull(attachmentId, "Attachment ID was not returned after upload to test run.");
    }

    @Owner("Pavel")
    @Test(groups = {"api"})
    public void retrieveAttachmentFromTestCase() throws IOException {
        attachmentId = AttachmentAdapter.addAttachmentToCase(caseId, attachment).getAttachmentId();

        byte[] downloadedAttachment = AttachmentAdapter.getAttachment(attachmentId);

        assertEquals(downloadedAttachment, Files.readAllBytes(attachment.toPath()),
                "Downloaded test case attachment content does not match the uploaded file.");
    }

    @Owner("Pavel")
    @Test(groups = {"api"})
    public void retrieveAttachmentFromTestRun() throws IOException {
        attachmentId = AttachmentAdapter.addAttachmentToRun(runId, attachment).getAttachmentId();

        byte[] downloadedAttachment = AttachmentAdapter.getAttachment(attachmentId);

        assertEquals(downloadedAttachment, Files.readAllBytes(attachment.toPath()),
                "Downloaded test run attachment content does not match the uploaded file.");
    }

    @Owner("Pavel")
    @Test(groups = {"api"})
    public void deleteAttachmentFromTestRun() {
        attachmentId = AttachmentAdapter.addAttachmentToRun(runId, attachment).getAttachmentId();
        String deletedAttachmentId = attachmentId;

        AttachmentAdapter.deleteAttachment(deletedAttachmentId);
        attachmentId = null;
        int statusCode = AttachmentAdapter.getAttachmentStatusCode(deletedAttachmentId);

        assertTrue(statusCode == 400 || statusCode == 404,
                "Deleted test run attachment is still retrievable. Status code: " + statusCode);
    }

    @Owner("Pavel")
    @Test(groups = {"api"})
    public void deleteAttachmentFromTestCase() {
        attachmentId = AttachmentAdapter.addAttachmentToCase(caseId, attachment).getAttachmentId();
        String deletedAttachmentId = attachmentId;

        AttachmentAdapter.deleteAttachment(deletedAttachmentId);
        attachmentId = null;
        int statusCode = AttachmentAdapter.getAttachmentStatusCode(deletedAttachmentId);

        assertTrue(statusCode == 400 || statusCode == 404,
                "Deleted test case attachment is still retrievable. Status code: " + statusCode);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        AttachmentAdapter.deleteAttachmentIfCreated(attachmentId);
        attachmentId = null;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws IOException {
        AttachmentAdapter.deleteAttachmentIfCreated(attachmentId);
        attachmentId = null;
        TestRunAdapter.deleteTestRunIfCreated(runId);
        runId = null;
        TestCaseAdapter.deleteTestCaseIfCreated(caseId);
        caseId = null;
        SectionAdapter.deleteSectionIfCreated(sectionId);
        sectionId = null;
        if (attachment != null) {
            Files.deleteIfExists(attachment.toPath());
        }
    }
}