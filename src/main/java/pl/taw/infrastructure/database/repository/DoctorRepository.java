package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.taw.infrastructure.database.repository.mapper.DoctorEntityMapper;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class DoctorRepository implements DoctorDAO {

    private final DoctorJpaRepository doctorJpaRepository;
    private final DoctorEntityMapper doctorEntityMapper;

    @Override
    public DoctorEntity findEntityById(Integer doctorId) {
        return doctorJpaRepository.findById(doctorId)
                .orElseThrow(
                        () -> new NotFoundException("Could not found doctor with id: [%s]"
                                .formatted(doctorId)));
    }

    // dodanie find zwracające DTO
    @Override
    public DoctorDTO findById(Integer doctorId) {
        return doctorJpaRepository.findById(doctorId)
                .map(doctorEntityMapper::mapFromEntity)
                .orElseThrow(() -> new NotFoundException(
                        "Could not found DoctorEntity with id: [%s]".formatted(doctorId)
                ));
    }

    @Override
    public void saveDoctor(DoctorEntity doctor) {
        doctorJpaRepository.save(doctor);
    }

    @Override
    public void updateDoctor(DoctorEntity doctor) {
        if (doctorJpaRepository.existsById(doctor.getDoctorId())) {
            doctorJpaRepository.save(doctor);
        } else {
            throw new NotFoundException("Could not found doctor with id: [%s]".formatted(doctor.getDoctorId()));
        }
    }

    @Override
    public void delete(DoctorEntity doctor) {
        if (doctorJpaRepository.existsById(doctor.getDoctorId())) {
            doctorJpaRepository.delete(doctor);
        } else {
            throw new NotFoundException(
                    "Could not find doctor with id: [%s]".formatted(doctor.getDoctorId()));
        }
    }

    @Override
    public List<DoctorEntity> findAll() {
        return doctorJpaRepository.findAll();
    }

    @Override
    public List<DoctorDTO> findAvailable() {
        return doctorJpaRepository.findAll().stream()
                .map(doctorEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> findBySpecialization(String specialization) {
        return doctorJpaRepository.findAll().stream()
                .filter(doctor -> specialization.equals(doctor.getTitle().toLowerCase()))
                .map(doctorEntityMapper::mapFromEntity)
                .toList();
    }

}
