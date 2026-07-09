package api.adapters;

import api.config.TestConfig;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.preemptive;


public class BaseAdapter {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(CONFIG.baseUrl())
            .setAuth(preemptive().basic(CONFIG.email(), CONFIG.password()))
            .setContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification ok200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

}