package ui.dto;

import com.github.javafaker.Faker;

public class ProjectFactory {

    public static Project getProject() {
        Faker faker = new Faker();
        return new Project(
                faker.company().name(),
                faker.lorem().sentence()
        );
    }
}
