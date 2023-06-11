package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.DoctorScheduleDAO;
import pl.taw.controller.dto.DoctorScheduleDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.DoctorScheduleEntity;
import pl.taw.infrastructure.database.repository.jpa.DoctorScheduleJpaRepository;
import pl.taw.infrastructure.database.repository.mapper.DoctorScheduleEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorScheduleRepository implements DoctorScheduleDAO {

    private final DoctorScheduleJpaRepository doctorScheduleJpaRepository;
    private final DoctorScheduleEntityMapper doctorScheduleEntityMapper;

    @Override
    public DoctorScheduleEntity findById(Integer id) {
        return doctorScheduleJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Could not found DoctorScheduleEntity with id: [%s]".formatted(id)
                ));
    }

    @Override
    public List<DoctorScheduleDTO> findScheduleByDoctorId(Integer doctorId) {
        return doctorScheduleJpaRepository.findAll().stream()
                .filter(schedule -> schedule.getDoctorId().equals(doctorId))
                .map(doctorScheduleEntityMapper::mapFromEntity)
                .toList();
    }

//    @Override
//    public List<DoctorScheduleDTO> findByDoctorIdRepo(int doctorId) {
//        return doctorScheduleJpaRepository.findAll().stream()
//                .filter(schedule -> schedule.getDoctor().getDoctorId().equals(doctorId))
//                .map(doctorScheduleEntityMapper::mapFromEntity)
//                .toList();
//    }
}
