package ui.dto;

import com.github.javafaker.Faker;


public class GroupFactory {

    public static Group getGroup() {
        Faker faker = new Faker();
        return new Group(
                faker.job().title()
        );
    }
}