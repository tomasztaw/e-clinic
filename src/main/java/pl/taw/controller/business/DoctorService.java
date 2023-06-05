package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.DoctorEntity;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorDAO doctorDAO;


    public DoctorEntity getDoctorById(int id) {
        return doctorDAO.findById(id);
    }

    public void saveDoctor(DoctorEntity doctor) {
        // Walidacja, logika biznesowa, itp.
        doctorDAO.saveDoctor(doctor);
    }


    @Transactional
    public List<DoctorDTO> findAvailable() {
        List<DoctorDTO> availableDoctors = doctorDAO.findAvailable();
        log.info("Available doctors: [{}]", availableDoctors.size());
        return availableDoctors;
    }

    public List<DoctorDTO> getDoctorsBySpecialization(String specialization) {
        return doctorDAO.findBySpecialization(specialization);
    }

    public List<String> getDoctorSpecializations() {
        return doctorDAO.findAll().stream()
                .map(DoctorEntity::getTitle)
                .distinct()
                .toList();
    }
}