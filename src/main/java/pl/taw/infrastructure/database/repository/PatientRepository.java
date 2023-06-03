package pl.taw.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.taw.infrastructure.database.repository.mapper.PatientEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PatientRepository implements PatientDAO {

    private final PatientJpaRepository patientJpaRepository;
    private final PatientEntityMapper patientEntityMapper;

    @Override
    public PatientEntity findById(int id) {
        return patientJpaRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Could not found patient with id: [%s]"
                                .formatted(id)));
    }

    @Override
    public void savePatient(PatientEntity patient) {
        patientJpaRepository.save(patient);
    }

    @Override
    public void updatePatient(PatientEntity patient) {
        if (patientJpaRepository.existsById(patient.getId())) {
            patientJpaRepository.save(patient);
        } else {
            throw new NotFoundException("Could not found patient with id: [%s]".formatted(patient.getId()));
        }
    }

    @Override
    public void delete(PatientEntity patient) {
        if (patientJpaRepository.existsById(patient.getId())) {
            patientJpaRepository.delete(patient);
        } else {
            throw new NotFoundException("Could not found patient with id: [%s]".formatted(patient.getId()));
        }
    }

    @Override
    public List<PatientEntity> findAll() {
        return patientJpaRepository.findAll();
    }

    @Override
    public List<PatientDTO> findAvailable() {
        return patientJpaRepository.findAll().stream()
                .map(patientEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDTO> findByPesel(String pesel) {
        return patientJpaRepository.findByPesel(pesel)
                .map(patientEntityMapper::mapFromEntity);
    }
}
