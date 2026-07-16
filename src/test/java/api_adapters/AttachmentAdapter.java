package api_adapters;

import api.models.attachments.*;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static api_adapters.BaseAdapter.*;
import static io.restassured.RestAssured.given;


public class AttachmentAdapter {

    private static final String PATH = "/index.php?/api/v2/";

    @Step("Add an attachment to test case {caseId}")
    public static AttachmentUploadRs addAttachmentToCase(Integer caseId, File attachment) {
        return given()
                .spec(multipartSpec())
                .urlEncodingEnabled(false)
                .pathParam("caseId", caseId)
                .multiPart("attachment", attachment)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_attachment_to_case/{caseId}")
                .then()
                .log().ifValidationFails()
                .spec(ok200)
                .extract()
                .as(AttachmentUploadRs.class, ObjectMapperType.GSON);
    }

    @Step("Add an attachment to test run {runId}")
    public static AttachmentUploadRs addAttachmentToRun(Integer runId, File attachment) {
        return given()
                .spec(multipartSpec())
                .urlEncodingEnabled(false)
                .pathParam("runId", runId)
                .multiPart("attachment", attachment)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_attachment_to_run/{runId}")
                .then()
                .log().ifValidationFails()
                .spec(ok200)
                .extract()
                .as(AttachmentUploadRs.class, ObjectMapperType.GSON);
    }

    @Step("Download attachment {attachmentId}")
    public static byte[] getAttachment(String attachmentId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("attachmentId", attachmentId)
                .log().ifValidationFails()
                .when()
                .get(PATH + "get_attachment/{attachmentId}")
                .then()
                .log().ifValidationFails()
                .spec(ok200)
                .extract()
                .asByteArray();
    }

    @Step("Get the status code for attachment {attachmentId}")
    public static int getAttachmentStatusCode(String attachmentId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("attachmentId", attachmentId)
                .when()
                .get(PATH + "get_attachment/{attachmentId}")
                .then()
                .extract()
                .statusCode();
    }

    @Step("Delete attachment {attachmentId}")
    public static void deleteAttachment(String attachmentId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("attachmentId", attachmentId)
                .log().ifValidationFails()
                .when()
                .post(PATH + "delete_attachment/{attachmentId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails();
    }

    private static RequestSpecification multipartSpec() {
        return given()
                .baseUri(BaseAdapter.CONFIG.baseUrl())
                .auth().preemptive().basic(BaseAdapter.CONFIG.email(), BaseAdapter.CONFIG.password())
                .filter(new AllureRestAssured());
    }
}