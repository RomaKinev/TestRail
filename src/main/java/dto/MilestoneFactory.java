package dto;

import com.github.javafaker.Faker;


public class MilestoneFactory {

    public static Milestone getMilestone() {
        Faker faker = new Faker();
        return new Milestone(
                faker.lorem().word(),
                faker.lorem().sentence()
        );
    }
}