package api.adapters;

import com.google.gson.Gson;
import api.models.sections.MoveSectionRq;
import api.models.sections.SectionRq;
import api.models.sections.SectionRs;
import io.restassured.mapper.ObjectMapperType;

import static api.adapters.BaseAdapter.ok200;
import static api.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;


public class SectionAdapter {

    private static final Gson GSON = new Gson();

    public static SectionRs createSection(SectionRq rq, String projectId) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .body(GSON.toJson(rq))
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
                .body(GSON.toJson(rq))
                .log().all()
                .when()
                .post("/index.php?/api/v2/update_section/{sectionId}")
                .then()
                .spec(ok200)
                .log().all()
                .extract()
                .as(SectionRs.class, ObjectMapperType.GSON);
    }

    public static SectionRs moveSection(Integer sectionId, MoveSectionRq moveSectionRq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("sectionId", sectionId)
                .body(GSON.toJson(moveSectionRq))
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