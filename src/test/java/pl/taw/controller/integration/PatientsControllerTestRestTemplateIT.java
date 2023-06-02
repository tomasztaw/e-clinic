package pl.taw.controller.integration;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.taw.controller.dto.PatientsDTO;
import pl.taw.controller.integration.configuration.AbstractIntegrationTest;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.util.DtoFixtures;

// Na początku test nie przechodził z AllArgs i zmieniłem na Required !!!!!
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientsControllerTestRestTemplateIT extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate testRestTemplate;

    @Test
    void thatEmployeesListingWorksCorrectly() {
        String url = "http://localhost:%s/clinic/patients".formatted(port);

        this.testRestTemplate.postForEntity(url, DtoFixtures.somePatient1(), PatientEntity.class);

        ResponseEntity<PatientsDTO> result = this.testRestTemplate.getForEntity(url, PatientsDTO.class);
        PatientsDTO body = result.getBody();
        Assertions.assertThat(body).isNotNull();
        Assertions.assertThat(body.getPatients()).hasSizeGreaterThan(0);
    }

}
