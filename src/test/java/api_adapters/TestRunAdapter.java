package api_adapters;

import api.models.attachments.TestCaseRs;
import api.models.attachments.TestRunRq;
import api.models.attachments.TestRunRs;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import java.util.List;

import static api_adapters.BaseAdapter.ok200;
import static api_adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;


public class TestRunAdapter {

    private static final String PATH = "/index.php?/api/v2/";


    @Step("Create a test run for a test case in project {projectId}")
    public static TestRunRs createTestRunForCase(String projectId, String runName, String runDescription,
                                                 TestCaseRs testCase) {
        TestRunRs testRunRs = createTestRun(TestRunRq.builder()
                .suite_id(testCase.getSuiteId())
                .name(runName)
                .description(runDescription)
                .include_all(false)
                .case_ids(List.of(testCase.getId()))
                .build(), projectId);
        testRunRs.setCreatedCaseId(testCase.getId());
        testRunRs.setCreatedSectionId(testCase.getCreatedSectionId());
        return testRunRs;
    }

    @Step("Create a test run in project {projectId}")
    public static TestRunRs createTestRun(TestRunRq rq, String projectId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_run/{projectId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(TestRunRs.class, ObjectMapperType.GSON);
    }

    @Step("Delete test run {runId}")
    public static void deleteTestRun(Integer runId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("runId", runId)
                .log().ifValidationFails()
                .when()
                .post(PATH + "delete_run/{runId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails();
    }

    @Step("Delete test run {runId} if it was created")
    public static void deleteTestRunIfCreated(Integer runId) {
        if (runId != null) {
            deleteTestRun(runId);
        }
    }
}