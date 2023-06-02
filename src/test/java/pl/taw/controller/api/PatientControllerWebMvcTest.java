package pl.taw.controller.api;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.mapper.PatientMapper;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.repository.PatientRepository;
import pl.taw.util.DtoFixtures;
import pl.taw.util.EntityFixtures;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientControllerWebMvcTest {

    private final MockMvc mockMvc;  // symuluje wywoływanie endpoint-ów po stronie przeglądarki

    @MockBean
    private PatientRepository patientRepository;
    @MockBean
    private PatientMapper patientMapper;


    @Test
    void thatPatientCanBeRetrieved() throws Exception {
        // given
        int patientId = 123;
        PatientEntity patientEntity = EntityFixtures.somePatient1().withId(patientId);
        PatientDTO patientDTO = DtoFixtures.somePatient1().withId(patientId);

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patientEntity));
        when(patientMapper.map(any(PatientEntity.class))).thenReturn(patientDTO);

        // when, then
        String endpoint = PatientController.PATIENTS + PatientController.PATIENT_ID;
        mockMvc.perform(get(endpoint, patientId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId", Matchers.is(patientDTO.getId())))
                .andExpect(jsonPath("$.name", Matchers.is(patientDTO.getName())))
                .andExpect(jsonPath("$.surname", Matchers.is(patientDTO.getSurname())))
                .andExpect(jsonPath("$.pesel", Matchers.is(patientDTO.getPesel())))
                .andExpect(jsonPath("$.phone", Matchers.is(patientDTO.getPhone())))
                .andExpect(jsonPath("$.email", Matchers.is(patientDTO.getEmail())));
    }

    @Test
    void thatEmailValidationWorksCorrectly() throws Exception {
        // given
        final var request = """
                {
                    "email": "badEmail"
                }
                """;

        // when, then
        mockMvc.perform(post(PatientController.PATIENTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorId", Matchers.notNullValue()));
    }

    @ParameterizedTest
    @MethodSource
    void thatPhoneValidationWorksCorrectly(Boolean correctPhone, String phone) throws Exception {
        // given
        final var request = """
                {
                    "phone": "%s"
                }
                """.formatted(phone);
        when(patientRepository.save(any(PatientEntity.class)))
                .thenReturn(EntityFixtures.somePatient1().withId(123));

        // when, then
        if (correctPhone) {
            String expectedRedirect = PatientController.PATIENTS
                    + PatientController.PATIENT_ID_RESULT.formatted(123);
            mockMvc.perform(post(PatientController.PATIENTS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isCreated())
                    .andExpect(redirectedUrl(expectedRedirect));
        } else {
            mockMvc.perform(post(PatientController.PATIENTS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorId", Matchers.notNullValue()));
        }
    }

    public static Stream<Arguments> thatPhoneValidationWorksCorrectly() {
        return Stream.of(
                Arguments.of(false, ""),
                Arguments.of(false, "+48 504 203 260@@"),
                Arguments.of(false, "+48.504.203.260"),
                Arguments.of(false, "+55(123) 456-78-90-"),
                Arguments.of(false, "+55(123) - 456-78-90"),
                Arguments.of(false, "504.203.260"),
                Arguments.of(false, " "),
                Arguments.of(false, "-"),
                Arguments.of(false, "()"),
                Arguments.of(false, "() + ()"),
                Arguments.of(false, "(21 777"),
                Arguments.of(false, "+48 (21)"),
                Arguments.of(false, "(+)"),
                Arguments.of(false, " 1"),
                Arguments.of(false, "1"),
                Arguments.of(false, "+48 (12) 504 203 260"),
                Arguments.of(false, "+48 (12) 504-203-260"),
                Arguments.of(false, "+48(12)504203260"),
                Arguments.of(false, "555-5555-555"),
                Arguments.of(true, "+48 504 203 260")
        );
    }

    @Test
    public void testPatientDetails() throws Exception {
        // Przygotowanie danych testowych
        Integer patientId = 1;
        PatientEntity patientEntity = EntityFixtures.somePatient1().withId(patientId);
        // Tworzenie obiektu PatientDTO, który zostanie zwrócony przez metodę
        PatientDTO expectedPatientDTO = DtoFixtures.somePatient1().withId(patientId);
        // Definiowanie zachowania mocka dla metody employeeRepository.findById()
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patientEntity));
        when(patientMapper.map(any(PatientEntity.class))).thenReturn(expectedPatientDTO);

        // Wywołanie metody i weryfikacja odpowiedzi
        String endpoint = PatientController.PATIENTS + PatientController.PATIENT_ID;
        mockMvc.perform(get(endpoint, patientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.patientId", Matchers.is(expectedPatientDTO.getId())))
                .andExpect(jsonPath("$.patientId").value(expectedPatientDTO.getId()));
    }

    @Test
    public void testPatientDetails_InvalidId() throws Exception {
        // Przygotowanie danych testowych
        Integer patientId = 1;
        // Definiowanie zachowania mocka dla metody employeeRepository.findById()
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // Wywołanie metody i weryfikacja odpowiedzi
        String endpoint = PatientController.PATIENTS + PatientController.PATIENT_ID;
        mockMvc.perform(get(endpoint, patientId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdatePatientPhone() throws Exception {
        // Przygotowanie danych testowych
        Integer patientId = 1;
        String newPhone = "+48 147 147 147";
        PatientEntity existingPatient = EntityFixtures.somePatient3().withId(patientId);
        PatientDTO patientDTO = DtoFixtures.somePatient1().withId(patientId);
        // Definiowanie zachowania mocka dla metody employeeRepository.findById()
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));
        when(patientMapper.map(any(PatientEntity.class))).thenReturn(patientDTO);

        // Wywołanie metody i weryfikacja odpowiedzi
        String endpoint = PatientController.PATIENTS + PatientController.PATIENT_UPDATE_PHONE;
        mockMvc.perform(patch(endpoint, patientId)
                        .param(newPhone))
                .andExpect(status().isOk());

        // Weryfikacja czy metoda employeeRepository.save() została wywołana z oczekiwanymi argumentami
        verify(patientRepository).save(existingPatient);
//        MatcherAssert.assertThat(existingPatient.getPhone(), Matchers.containsString(newPhone));
        Assertions.assertThat(existingPatient.getPhone()).isEqualTo(newPhone);
    }

    @Test
    public void testUpdatePatientPhone_InvalidId() throws Exception {
        // Przygotowanie danych testowych
        Integer patientId = 1;
        String newPhone = "+48 147 147 147";
        // Definiowanie zachowania mocka dla metody employeeRepository.findById()
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // Wywołanie metody i weryfikacja odpowiedzi
        String endpoint = PatientController.PATIENTS + PatientController.PATIENT_UPDATE_PHONE;
        mockMvc.perform(patch(endpoint, patientId)
                        .param(newPhone))
                .andExpect(status().isNotFound());

        // Weryfikacja czy metoda employeeRepository.save() nie została wywołana
        verify(patientRepository, Mockito.never()).save(Mockito.any());
    }

}
