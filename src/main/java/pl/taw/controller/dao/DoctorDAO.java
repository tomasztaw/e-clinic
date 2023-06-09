package pl.taw.controller.dao;

import pl.taw.controller.dto.DoctorDTO;
import pl.taw.infrastructure.database.entity.DoctorEntity;

import java.util.List;

public interface DoctorDAO {

    DoctorEntity findById(int id);

    // dodanie find zwracające DTO
    DoctorDTO findById(Integer doctorId);

    void saveDoctor(DoctorEntity doctor);

    void updateDoctor(DoctorEntity doctor);

    void delete(DoctorEntity doctor);

    List<DoctorEntity> findAll();

    List<DoctorDTO> findAvailable();

    List<DoctorDTO> findBySpecialization(String specialization);

}
