package ui.dto;

import com.github.javafaker.Faker;

public class TestRunFactory {

    public static TestRun getRun() {
        Faker faker = new Faker();
        return TestRun.builder()
                .name("Run_" + faker.lorem().word())
                .description(faker.lorem().sentence())
                .build();
    }
}
