package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.*;
import pl.taw.controller.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;
    private VisitDAO visitDAO;

    public List<PrescriptionDTO> getDoctorPrescriptions(Integer doctorId) {
        return prescriptionDAO.findByDoctorId(doctorId);
    }

    public List<PrescriptionDTO> getPatientPrescriptions(Integer patientId) {
        return prescriptionDAO.findByPatientId(patientId);
    }

    public PrescriptionDTO findPrescriptionFromVisit(VisitDTO visitDTO) {
        LocalDate date = visitDTO.getEndTime().toLocalDate();
        PrescriptionsDTO byDate = prescriptionDAO.findByDate(date);
        VisitsDTO visitByDate = visitDAO.findByDate(date);

        return visitByDate.getVisits().stream()
                .flatMap(visit -> byDate.getPrescriptions().stream()
                        .filter(presc -> presc.getCreatedAt().equals(visit.getEndTime())
                        && presc.getPatientId().equals(visit.getPatientId())
                        && presc.getDoctorId().equals(visit.getDoctorId())))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "No matching prescription for visit with id: [%s]".formatted(visitDTO.getVisitId())));
    }
}
