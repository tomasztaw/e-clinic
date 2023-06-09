package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PrescriptionDTO;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;

    public List<PrescriptionDTO> getDoctorPrescriptions(Integer doctorId) {
        return prescriptionDAO.findByDoctorId(doctorId);
    }

    public List<PrescriptionDTO> getPatientPrescriptions(Integer patientId) {
        return prescriptionDAO.findByPatientId(patientId);
    }
}
