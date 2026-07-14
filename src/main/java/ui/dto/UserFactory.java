package ui.dto;

import com.github.javafaker.Faker;


public class UserFactory {

    public static User getUser() {
        Faker faker = new Faker();
        return new User(
                faker.name().fullName(),
                faker.internet().emailAddress()
        );
    }
}