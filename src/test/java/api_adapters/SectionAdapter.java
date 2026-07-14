package api_adapters;

import api.models.sections.*;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;

import static api_adapters.BaseAdapter.ok200;
import static api_adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;


public class SectionAdapter {

    private static final String PATH = "/index.php?/api/v2/";


    @Step("Создаем секцию")
    public static SectionRs createSection(SectionRq rq, String projectId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .body(rq)
                .log().all() //поудалять по проекту
                .when()
                .post(PATH + "add_section/{projectId}")
                .then()
                .spec(ok200)
                .log().all() //поудалять по проекту
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static SectionRs updateSection(Integer sectionId, SectionRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().all()//поудалять по проекту
                .when()
                .post(PATH + "update_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().all()//поудалять по проекту
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static SectionRs moveSection(Integer sectionId, MoveSectionRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().all()//поудалять по проекту
                .when()
                .post(PATH + "move_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().all()//поудалять по проекту
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static void deleteSection(Integer sectionId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .log().all()//поудалять по проекту
                .when()
                .post(PATH + "delete_section/{sectionId}")
                .then()
                .spec(ok200) //поудалять по проекту
                .log().all();
    }

    public static void deleteSectionIfCreated(Integer sectionId) {
        if (sectionId != null) {
            deleteSection(sectionId);
        }
    }
}