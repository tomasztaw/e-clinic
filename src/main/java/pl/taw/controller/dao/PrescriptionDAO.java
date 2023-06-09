package pl.taw.controller.dao;

import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;

import java.util.List;

public interface PrescriptionDAO {

    PrescriptionEntity findById(Integer prescriptionId);

    List<PrescriptionDTO> findByDoctorId(Integer doctorId);

    List<PrescriptionDTO> findByPatientId(Integer patientId);

}
