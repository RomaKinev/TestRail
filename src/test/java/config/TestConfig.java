package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("email")
    String email();

    @Key("password")
    String password();
}