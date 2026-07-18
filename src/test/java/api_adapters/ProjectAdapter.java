package api_adapters;

import api.models.projects.*;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

import static api_adapters.BaseAdapter.*;
import static io.restassured.RestAssured.given;


public class ProjectAdapter {

    private static final String PATH = "/index.php?/api/v2/";

    @Step("Get all projects")
    public static ProjectsRs getProjects() {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .log().ifValidationFails()
                .when()
                .get(PATH + "get_projects")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(ProjectsRs.class, ObjectMapperType.GSON);
    }

    @Step("Get projects list status code")
    public static int getProjectsStatusCode(@Param(mode = Parameter.Mode.MASKED) String email,
                                            @Param(mode = Parameter.Mode.MASKED) String password) {
        return given()
                .baseUri(CONFIG.baseUrl())
                .auth().preemptive().basic(email, password)
                .contentType(ContentType.JSON)
                .urlEncodingEnabled(false)
                .when()
                .get(PATH + "get_projects")
                .then()
                .extract()
                .statusCode();
    }

    @Step("Create a project")
    public static ProjectRs createProject(ProjectRq rq) {
        return given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .body(rq)
                .log().ifValidationFails()
                .when()
                .post(PATH + "add_project")
                .then()
                .spec(ok200)
                .log().ifValidationFails()
                .extract()
                .as(ProjectRs.class, ObjectMapperType.GSON);
    }

    @Step("Delete project {projectId}")
    public static void deleteProject(Integer projectId) {
        given()
                .spec(spec)
                .urlEncodingEnabled(false)
                .pathParam("projectId", projectId)
                .log().ifValidationFails()
                .when()
                .post(PATH + "delete_project/{projectId}")
                .then()
                .spec(ok200)
                .log().ifValidationFails();
    }

    @Step("Delete project {projectId} if it was created")
    public static void deleteProjectIfCreated(Integer projectId) {
        if (projectId != null) {
            deleteProject(projectId);
        }
    }
}
