package api_adapters;

import api.models.attachments.*;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import java.util.List;

import static api_adapters.BaseAdapter.*;
import static io.restassured.RestAssured.given;


public class TestRunAdapter {

    private static final String PATH = "/index.php?/api/v2/";

    @Step("Create a test run for a test case in project {projectId}")
    public static TestRunRs createTestRunForCase(String projectId, String runName, String runDescription,
                                                 TestCaseRs testCase) {
        return createTestRun(TestRunRq.builder()
                .suite_id(testCase.getSuiteId())
                .name(runName)
                .description(runDescription)
                .include_all(false)
                .case_ids(List.of(testCase.getId()))
                .build(), projectId);
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
}