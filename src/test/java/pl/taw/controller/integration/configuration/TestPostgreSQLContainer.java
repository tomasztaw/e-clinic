/**
 * Klasa konfiguracyjna podpowiedziana przez GPT, nie działa!
 */

package pl.taw.controller.integration.configuration;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestPostgreSQLContainer extends PostgreSQLContainer<TestPostgreSQLContainer> {

    private static final String IMAGE_VERSION = "postgres:15.3";
    private static TestPostgreSQLContainer container;

    private TestPostgreSQLContainer() {
        super(IMAGE_VERSION);
    }

    public static TestPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new TestPostgreSQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }

    @Override
    public void stop() {
        // Nie zatrzymujemy kontenera podczas zakończenia testów
    }
}
