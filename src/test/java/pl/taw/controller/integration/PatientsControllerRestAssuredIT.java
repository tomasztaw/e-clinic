package pl.taw.controller.integration;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.PatientsDTO;
import pl.taw.controller.integration.configuration.RestAssuredIntegrationTestBase;
import pl.taw.controller.integration.support.PatientsControllerTestSupport;
import pl.taw.controller.integration.support.WireMockTestSupport;
import pl.taw.util.DtoFixtures;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientsControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements PatientsControllerTestSupport, WireMockTestSupport {

    @Test
    void thatPatientsListCanBeRetrievedCorrectly() {
        // given
        PatientDTO patient1 = DtoFixtures.somePatient1();
        PatientDTO patient2 = DtoFixtures.somePatient2();

        // when
        savePatient(patient1);
        savePatient(patient2);

        PatientsDTO patients = listPatients();

        // then
        assertThat(patients.getPatients())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("patientId")
                .containsNull();
//                .containsAnyOf(patient1.withPets(Set.of()), patient2.withPets(Set.of()));
//                .containsExactlyInAnyOrder(patient1.withPets(Set.of()), patient2.withPets(Set.of()));
    }

    @Test
    void thatPatientCanBeCreateCorrectly() {
        // given
        PatientDTO patient1 = DtoFixtures.somePatient1();

        // when
        ExtractableResponse<Response> response = savePatient(patient1);

        // then
        String responseAsString = response.body().asString();
        assertThat(responseAsString).isEmpty();
        assertThat(response.headers().get("Location").getValue())
                .matches(Pattern.compile("/patients/\\d"));
    }

    @Test
    void thatCreatedPatientCanBeRetrievedCorrectly() {
        // given
        PatientDTO patient1 = DtoFixtures.somePatient1();

        // when
        ExtractableResponse<Response> response = savePatient(patient1);
        String patientDetailsPath = response.headers().get("Location").getValue();

        PatientDTO patient = getPatient(patientDetailsPath);

        // then
        assertThat(patient)
                .usingRecursiveComparison()
                .ignoringFields("patientId")
                .isEqualTo(patient1.withId(patient1.getId()));
    }


}
