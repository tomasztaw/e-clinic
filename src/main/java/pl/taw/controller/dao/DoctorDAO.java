package pl.taw.controller.dao;

import pl.taw.controller.dto.DoctorDTO;
import pl.taw.infrastructure.database.entity.DoctorEntity;

import java.util.List;
import java.util.Optional;

public interface DoctorDAO {

    DoctorEntity findById(int id);

    void saveDoctor(DoctorEntity doctor);

    void updateDoctro(DoctorEntity doctor);

    void delete(DoctorEntity doctor);

    List<DoctorEntity> findAll();

    List<DoctorDTO> findAvailable();

    Optional<DoctorDTO> findByFullName(String fullName);

}
