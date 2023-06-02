/**
 * To jest klasa testowa podpowiedziana przez GPT.
 * Nie działa, błędna konfiguracja
 * Teraz działa, ale nie wiem dokładnie dlaczego :)
 */

package pl.taw.controller.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.taw.controller.integration.configuration.TestPostgreSQLContainer;
import pl.taw.infrastructure.database.repository.PatientRepository;

@Testcontainers
@SpringBootTest
public class E2ETest {

    @Container
    private static final TestPostgreSQLContainer postgresContainer = TestPostgreSQLContainer.getInstance();

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void someE2ETest() {
        // Testy E2E z użyciem kontenera PostgreSQL
    }

    // Inne testy E2E
}

