package dto;

import com.github.javafaker.Faker;

public class TestCaseFactory {

    public static TestCase getTestCase() {
        Faker faker = new Faker();
        return TestCase.builder()
                .title(faker.lorem().sentence(3))
                .build();
    }
}
