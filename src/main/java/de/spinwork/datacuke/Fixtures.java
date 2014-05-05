package de.spinwork.datacuke;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages fixture data in an own Map, so you may add and read variables with different kinds of data you want to
 * share between implementation steps. The sharing is managed with the cucumber-glue scope.
 *
 * In Groovy cucumber steps you can use the static getLoader method for accessing the spring application context.
 */
@Component
public class Fixtures {

    private Map<String, ?> data = new HashMap<>();
    private Map<String, UseCase<?>> usecases = new HashMap<>();

    public static Fixtures getLoader() {
        return new AnnotationConfigApplicationContext(FixtureLoaderConfiguration.class).getBean(Fixtures.class);
    }

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }


    public Map<String, ? extends UseCase> getUsecases() {
        return usecases;
    }

    public void setUsecases(Map<String, UseCase<?>> usecases) {
        this.usecases = usecases;
    }
}
