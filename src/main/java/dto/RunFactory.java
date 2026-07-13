package dto;

import com.github.javafaker.Faker;

public class RunFactory {

    public static Run getRun() {
        Faker faker = new Faker();
        return Run.builder()
                .name("Run_" + faker.lorem().word())
                .description(faker.lorem().sentence())
                .build();
    }
}
