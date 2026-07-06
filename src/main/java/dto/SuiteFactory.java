package dto;

import com.github.javafaker.Faker;

public class SuiteFactory {

    public static Suite getSuite() {
        Faker faker = new Faker();
        return Suite.builder()
                .name("Suite_" + faker.lorem().word())
                .description(faker.lorem().sentence())
                .build();
    }
}