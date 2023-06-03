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
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class DoctorRepository implements DoctorDAO {

    private final DoctorJpaRepository doctorJpaRepository;
    private final DoctorEntityMapper doctorEntityMapper;

    @Override
    public DoctorEntity findById(int id) {
        return doctorJpaRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Could not found doctor with id: [%s]"
                                .formatted(id)));
    }

    @Override
    public void saveDoctor(DoctorEntity doctor) {
        doctorJpaRepository.save(doctor);
    }

    @Override
    public void updateDoctro(DoctorEntity doctor) {
        if (doctorJpaRepository.existsById(doctor.getId())) {
            doctorJpaRepository.save(doctor);
        } else {
            throw new NotFoundException("Could not found doctor with id: [%s]".formatted(doctor.getId()));
        }
    }

    @Override
    public void delete(DoctorEntity doctor) {
        if (doctorJpaRepository.existsById(doctor.getId())) {
            doctorJpaRepository.delete(doctor);
        } else {
            throw new NotFoundException(
                    "Could not find doctor with id: [%s]".formatted(doctor.getId()));
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
    public Optional<DoctorDTO> findByFullName(String fullName) {
        String[] splitName = fullName.split(" ");
        return doctorJpaRepository.findByFullName(splitName[1], splitName[2], splitName[0])
                .map(doctorEntityMapper::mapFromEntity);
    }

}
