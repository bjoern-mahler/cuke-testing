package de.spinwork.datacuke;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource("cucumber/runtime/java/spring/cucumber-glue.xml")
public class FixtureLoaderConfiguration {

    @Bean
    @Scope("cucumber-glue")
    Fixtures fixtureLoader() {
        return new Fixtures();
    }
}
