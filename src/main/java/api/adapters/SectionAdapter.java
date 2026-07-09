package api.adapters;

import api.models.sections.MoveSectionRq;
import api.models.sections.SectionRq;
import api.models.sections.SectionRs;
import io.restassured.mapper.ObjectMapperType;

import static api.adapters.BaseAdapter.ok200;
import static api.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;


public class SectionAdapter {

    public static SectionRs createSection(SectionRq rq, String projectId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .body(rq)
                .log().all()
                .when()
                .post("/index.php?/api/v2/add_section/{projectId}")
                .then()
                .spec(ok200)
                .log().all()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static SectionRs updateSection(Integer sectionId, SectionRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().all()
                .when()
                .post("/index.php?/api/v2/update_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().all()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static SectionRs moveSection(Integer sectionId, MoveSectionRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(rq)
                .log().all()
                .when()
                .post("/index.php?/api/v2/move_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().all()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static void deleteSection(Integer sectionId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .log().all()
                .when()
                .post("/index.php?/api/v2/delete_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().all();
    }

    public static void deleteSectionIfCreated(Integer sectionId) {
        if (sectionId != null) {
            deleteSection(sectionId);
        }
    }
}