package api_adapters;

import api.models.results.*;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import static api_adapters.BaseAdapter.*;
import static io.restassured.RestAssured.given;


public class TestRunResultAdapter {

    private static final String PATH = "/index.php?/api/v2/";

    @Step("Add a result for case {caseId} in run {runId}")
    public static TestRunResultRs addResultForCase(Integer runId, Integer caseId, TestRunResultRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("runId", runId)
                .pathParam("caseId", caseId)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_result_for_case/{runId}/{caseId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(TestRunResultRs.class, ObjectMapperType.GSON);
    }

    @Step("Get results for run {runId}")
    public static TestRunResultsRs getResultsForRun(Integer runId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("runId", runId)
                .log().ifValidationFails()
                .when()
                .get(PATH + "get_results_for_run/{runId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(TestRunResultsRs.class, ObjectMapperType.GSON);
    }
}
