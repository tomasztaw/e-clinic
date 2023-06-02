package pl.taw.controller.integration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.taw.controller.api.PatientController;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.PatientsDTO;

public interface PatientsControllerTestSupport {

    RequestSpecification requestSpecification();

    default PatientsDTO listPatients() {
        return requestSpecification()
                .get(PatientController.PATIENTS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(PatientsDTO.class);
    }

    default PatientDTO getPatient(final String path) {
        return requestSpecification()
                .get(path)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(PatientDTO.class);
    }

    default PatientDTO getPatientById(final Integer patientId) {
        return requestSpecification()
                .get(PatientController.PATIENTS + PatientController.PATIENT_ID, patientId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(PatientDTO.class);
    }

    default ExtractableResponse<Response> savePatient(final PatientDTO patientDto) {
        return requestSpecification()
                .body(patientDto)
                .post(PatientController.PATIENTS)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }

//    default void updatePatientBy___(final Integer patientId, final Long petId) {
//        String endpoint = PatientController.PATIENTS + PatientController.EMPLOYEE_UPDATE_PET;
//        requestSpecification()
//                .patch(endpoint, patientId, petId)
//                .then()
//                .statusCode(HttpStatus.OK.value());
//    }
}
