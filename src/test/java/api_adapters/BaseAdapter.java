package api_adapters;

import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.*;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.preemptive;


public class BaseAdapter {

    public static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
    public static final AllureRestAssured ALLURE_FILTER = new AllureRestAssured();
    public static final RestAssuredConfig SECURE_LOG_CONFIG = RestAssuredConfig.config()
            .logConfig(LogConfig.logConfig().blacklistHeader("Authorization"));

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(CONFIG.baseUrl())
            .setAuth(preemptive().basic(CONFIG.email(), CONFIG.password()))
            .setContentType(ContentType.JSON)
            .setConfig(SECURE_LOG_CONFIG)
            .addFilter(ALLURE_FILTER)
            .build();

    public static ResponseSpecification ok200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}