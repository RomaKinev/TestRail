package api_adapters;

import api.models.sections.*;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import static api_adapters.BaseAdapter.*;
import static io.restassured.RestAssured.given;


public class SectionAdapter {

    private static final String PATH = "/index.php?/api/v2/";
    
    @Step("Create a section in project {projectId}")
    public static SectionRs createSection(SectionRq rq, String projectId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_section/{projectId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    @Step("Update section {sectionId}")
    public static SectionRs updateSection(Integer sectionId, SectionRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "update_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    @Step("Move section {sectionId}")
    public static SectionRs moveSection(Integer sectionId, MoveSectionRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "move_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    @Step("Delete section {sectionId}")
    public static void deleteSection(Integer sectionId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .log().ifValidationFails()
                .when()
                .post(PATH + "delete_section/{sectionId}")
                .then()
                .spec(ok200) 
                .log().ifValidationFails();
    }

    @Step("Delete section {sectionId} if it was created")
    public static void deleteSectionIfCreated(Integer sectionId) {
        if (sectionId != null) {
            deleteSection(sectionId);
        }
    }
}