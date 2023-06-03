package pl.taw.controller.dao;

import pl.taw.controller.dto.PatientDTO;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {

    PatientEntity findById(int id);

    void savePatient(PatientEntity patient);

    void updatePatient(PatientEntity patient);

    void delete(PatientEntity patient);

    List<PatientEntity> findAll();

    List<PatientDTO> findAvailable();

    Optional<PatientDTO> findByPesel(String pesel);

}
