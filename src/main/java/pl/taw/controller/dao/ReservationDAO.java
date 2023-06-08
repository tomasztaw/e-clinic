package pl.taw.controller.dao;

import pl.taw.controller.dto.ReservationDTO;
import pl.taw.infrastructure.database.entity.ReservationEntity;

import java.util.List;

public interface ReservationDAO {

    ReservationEntity findById(Integer id);

    List<ReservationDTO> findReservationByDoctorId(Integer doctorId);

}
