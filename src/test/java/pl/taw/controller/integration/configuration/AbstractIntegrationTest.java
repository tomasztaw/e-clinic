package pl.taw.controller.integration.configuration;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import pl.taw.EClinicApplication;
import pl.taw.infrastructure.database.repository.jpa.PatientJpaRepository;

@ActiveProfiles("test")
@Import(PersistenceContainerTestConfiguration.class)
@SpringBootTest(classes = {EClinicApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

    @Autowired
    private PatientJpaRepository patientJpaRepository;

    // czyszczenie bazy po każdym teście
    @AfterEach
    public void after() {
        patientJpaRepository.deleteAll();
    }
}
