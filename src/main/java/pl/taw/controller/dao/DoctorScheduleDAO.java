package pl.taw.controller.dao;

import pl.taw.controller.dto.DoctorScheduleDTO;
import pl.taw.infrastructure.database.entity.DoctorScheduleEntity;

import java.util.List;

public interface DoctorScheduleDAO {

    DoctorScheduleEntity findById(Integer id);

    List<DoctorScheduleDTO> findScheduleByDoctorId(Integer doctorId);

}
