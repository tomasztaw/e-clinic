package pl.taw.controller.dao;

import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.controller.dto.PrescriptionsDTO;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionDAO {

    PrescriptionEntity findById(Integer prescriptionId);

    List<PrescriptionDTO> findByDoctorId(Integer doctorId);

    List<PrescriptionDTO> findByPatientId(Integer patientId);

    PrescriptionsDTO findByDate(LocalDate date);

}
