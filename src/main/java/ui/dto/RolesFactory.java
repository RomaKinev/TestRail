package ui.dto;

import com.github.javafaker.Faker;


public class RolesFactory {

    public static Roles getRole() {
        Faker faker = new Faker();
        return new Roles(
                faker.job().title()
        );
    }
}