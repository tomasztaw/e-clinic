package pl.taw.controller.dao;

import pl.taw.controller.dto.VisitDTO;
import pl.taw.controller.dto.VisitsDTO;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.time.LocalDate;
import java.util.List;

public interface VisitDAO {

    VisitEntity findById(Integer visitId);

    VisitDTO findDTOById(Integer visitId);

    List<VisitDTO> findByStatus(String status);

    List<VisitDTO> findByDoctorId(Integer doctorId);

    List<VisitDTO> findByPatientId(Integer patientId);

    List<VisitDTO> findAll();

    VisitsDTO findByDate(LocalDate date);

    void saveVisit(VisitEntity newVisit);

    void delete(VisitEntity visitForDelete);
}
