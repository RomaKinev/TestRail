package api_adapters;

import api.models.attachments.TestCaseRq;
import api.models.attachments.TestCaseRs;
import api.models.sections.*;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import static api_adapters.BaseAdapter.ok200;
import static api_adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;


public class TestCaseAdapter {

    private static final String PATH = "/index.php?/api/v2/";


    @Step("Создаем тест-кейс с секцией")
    public static TestCaseRs createTestCaseWithSection(String projectId, String sectionName, String sectionDescription,
                                                       String caseTitle) {
        SectionRs sectionRs = SectionAdapter.createSection(SectionRq.builder()
                .name(sectionName)
                .description(sectionDescription)
                .build(), projectId);

        TestCaseRs testCaseRs = createTestCase(TestCaseRq.builder()
                .title(caseTitle)
                .build(), sectionRs.getId());
        testCaseRs.setCreatedSectionId(sectionRs.getId());
        return testCaseRs;
    }

    @Step("Создаем тест-кейс")
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

    @Step("Удаляем тест-кейс")
    public static void deleteTestCase(Integer caseId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("caseId", caseId)
                .log().ifValidationFails()
                .when()
                .post(PATH + "delete_case/{caseId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails();
    }

    public static void deleteTestCaseIfCreated(Integer caseId) {
        if (caseId != null) {
            deleteTestCase(caseId);
        }
    }
}