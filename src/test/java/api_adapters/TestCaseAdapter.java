package api_adapters;

import api.models.attachments.*;
import api.models.cases.TestCasesRs;
import api.models.sections.*;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import static api_adapters.BaseAdapter.*;
import static io.restassured.RestAssured.given;


public class TestCaseAdapter {

    private static final String PATH = "/index.php?/api/v2/";

    @Step("Create a test case with a section in project {projectId}")
    public static TestCaseRs createTestCaseWithSection(String projectId, String sectionName, String sectionDescription,
                                                       String caseTitle) {
        SectionRs sectionRs = SectionAdapter.createSection(SectionRq.builder()
                .name(sectionName)
                .description(sectionDescription)
                .build(), projectId);

        return createTestCase(TestCaseRq.builder()
                .title(caseTitle)
                .build(), sectionRs.getId());
    }

    @Step("Create a test case in section {sectionId}")
    public static TestCaseRs createTestCase(TestCaseRq rq, Integer sectionId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_case/{sectionId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(TestCaseRs.class, ObjectMapperType.GSON);
    }

    @Step("Get test cases of project {projectId}")
    public static TestCasesRs getCases(String projectId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .log().ifValidationFails()
                .when()
                .get(PATH + "get_cases/{projectId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(TestCasesRs.class, ObjectMapperType.GSON);
    }

    @Step("Update test case {caseId}")
    public static TestCaseRs updateCase(Integer caseId, TestCaseRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("caseId", caseId)
                .body(rq, ObjectMapperType.GSON)
                .log().ifValidationFails()
                .when()
                .post(PATH + "update_case/{caseId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(TestCaseRs.class, ObjectMapperType.GSON);
    }
}