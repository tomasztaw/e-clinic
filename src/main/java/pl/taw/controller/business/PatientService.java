package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PatientService {

    private final PatientDAO patientDAO;

    public PatientEntity getPatientById(int id) {
        return patientDAO.findById(id);
    }

    public void savePatient(PatientEntity patient) {
        patientDAO.savePatient(patient);
    }

    @Transactional
    public List<PatientDTO> findAvailable() {
        List<PatientDTO> availablePatients = patientDAO.findAvailable();
        log.info("Available patients: [{}]", availablePatients.size());
        return availablePatients;
    }

    @Transactional
    public PatientDTO findPatientByPesel(String pesel) {
        Optional<PatientDTO> optionalPatientDTO = patientDAO.findByPesel(pesel);
        if (optionalPatientDTO.isEmpty()) {
            throw new NotFoundException("Could not find patient by PESEL: [%s]".formatted(pesel));
        }
        return optionalPatientDTO.get();
    }


}
