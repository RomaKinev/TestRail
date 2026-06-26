package dto;

import com.github.javafaker.Faker;


public class MilestoneFactory {

    public static Project getMilestone() {
        Faker faker = new Faker();
        return new Project(
                faker.company().name(),
                faker.lorem().sentence()
        );
    }
}