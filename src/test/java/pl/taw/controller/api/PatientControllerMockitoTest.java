package pl.taw.controller.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.mapper.PatientMapper;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.repository.PatientRepository;
import pl.taw.util.DtoFixtures;
import pl.taw.util.EntityFixtures;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerMockitoTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientController patientController;

    @Test
    void thatRetrievingPatientWorksCorrectly() {
        // given
        Integer patientId = 10;
        PatientEntity patientEntity = EntityFixtures.somePatient1();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patientEntity));
        when(patientMapper.map(patientEntity)).thenReturn(DtoFixtures.somePatient1());

        // when
        PatientDTO result = patientController.patientDetails(patientId);

        // then
        assertThat(result).isEqualTo(DtoFixtures.somePatient1());
    }

    @Test
    void shouldReturnAllPatients() {
        // given
        // tworzymy listy encji/dto z klas pomocniczych:
        List<PatientEntity> allPatients = List.of(EntityFixtures.somePatient1(),
                EntityFixtures.somePatient2(), EntityFixtures.somePatient3());
        List<PatientDTO> expectedDTOs = Arrays.asList(
                DtoFixtures.somePatient1(), DtoFixtures.somePatient2());
        when(patientRepository.findAll()).thenReturn(allPatients);
        when(patientMapper.map(allPatients.get(0))).thenReturn(DtoFixtures.somePatient1());

        // when
        var result = patientController.patientsList();

        // then
        assertThat(result.getPatients().size()).isEqualTo(allPatients.size());
        verify(patientRepository, times(1)).findAll();
        verify(patientMapper, times(1)).map(allPatients.get(0));
    }

    @Test
    void thatSavingPatientWorksCorrectly() {
        // given
        when(patientRepository.save(any(PatientEntity.class)))
                .thenReturn(EntityFixtures.somePatient1().withId(123));

        // when
        ResponseEntity<?> result = patientController.addPatient(DtoFixtures.somePatient1());

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testAddPatient() {
        // Arrange
        PatientDTO patientDTO = DtoFixtures.somePatient2();
        PatientEntity savedPatientEntity = EntityFixtures.somePatient3();
        when(patientRepository.save(any(PatientEntity.class))).thenReturn(savedPatientEntity);

        // Act
        ResponseEntity<PatientDTO> response = patientController.addPatient(patientDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(patientRepository, times(1)).save(any(PatientEntity.class));
    }

    @Test
    public void testUpdatePatient_Success() {
        // Arrange
        Integer patientId = 1;
        PatientDTO patientDTO = DtoFixtures.somePatient2();

        PatientEntity existingPatient = EntityFixtures.somePatient1();
        existingPatient.setName(patientDTO.getName());
        existingPatient.setSurname(patientDTO.getSurname());
        existingPatient.setPesel(patientDTO.getPesel());
        existingPatient.setPhone(patientDTO.getPhone());
        existingPatient.setEmail(patientDTO.getEmail());

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // Act
        ResponseEntity<?> response = patientController.updatePatient(patientId, patientDTO);

        // Assert
        verify(patientRepository).findById(patientId);
        verify(patientRepository).save(existingPatient);
        assertThat(ResponseEntity.ok().build()).isEqualTo(response);
        assertThat(patientDTO.getName()).isEqualTo(existingPatient.getName());
        assertThat(patientDTO.getSurname()).isEqualTo(existingPatient.getSurname());
        assertThat(patientDTO.getPesel()).isEqualTo(existingPatient.getPesel());
        assertThat(patientDTO.getPhone()).isEqualTo(existingPatient.getPhone());
        assertThat(patientDTO.getEmail()).isEqualTo(existingPatient.getEmail());
    }

    @Test
    void shouldDeletePatientCorrectly() {
        // given
        Integer patientId = 1;
        var patientOpt = patientRepository.findById(patientId);
        PatientEntity existingPatient = EntityFixtures.somePatient3();
        when(patientRepository.findById(patientId)).thenReturn(Optional.ofNullable(existingPatient));

        // when
        ResponseEntity<?> response = patientController.deletePatient(patientId);

        // then
        assertThat(response).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    public void testDeletePatient_ExistingPatient() {
        // Mockowanie zwracanej wartości przez repository
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(EntityFixtures.somePatient1()));

        // Wywołanie metody kontrolera
        ResponseEntity<?> response = patientController.deletePatient(1);

        // Sprawdzenie, czy metoda deleteById została wywołana z prawidłowym argumentem
        verify(patientRepository).deleteById(1);

        // Sprawdzenie, czy odpowiedź ma status 200 (OK)
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
    }

    @Test
    public void testDeletePatient_NonExistingPatient() {
        // Mockowanie zwracanej wartości przez repository
        when(patientRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Wywołanie metody kontrolera
        ResponseEntity<?> response = patientController.deletePatient(1);

        // Sprawdzenie, czy metoda deleteById nie została wywołana
        verify(patientRepository, never()).deleteById(anyInt());

        // Sprawdzenie, czy odpowiedź ma status 404 (Not Found)
        assertThat(HttpStatus.NOT_FOUND).isEqualTo(response.getStatusCode());
    }

}