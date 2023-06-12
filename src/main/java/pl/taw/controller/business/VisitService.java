package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.controller.dto.PrescriptionsDTO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.controller.dto.VisitsDTO;
import pl.taw.controller.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VisitService {

    private final VisitDAO visitDAO;
    private final PrescriptionDAO prescriptionDAO;

    public List<VisitDTO> getDoctorVisits(Integer doctorId) {
        return visitDAO.findByDoctorId(doctorId);
    }

    public List<VisitDTO> getPatientVisits(Integer patientId) {
        return visitDAO.findByPatientId(patientId);
    }

    public VisitDTO findVisitFromPrescription(PrescriptionDTO prescriptionDTO) {
        LocalDate date = prescriptionDTO.getCreatedAt().toLocalDate();
        PrescriptionsDTO byDate = prescriptionDAO.findByDate(date);
        VisitsDTO visitByDate = visitDAO.findByDate(date);

        return byDate.getPrescriptions().stream()
                .flatMap(presc -> visitByDate.getVisits().stream()
                        .filter(visit -> visit.getEndTime().equals(presc.getCreatedAt())
                        && visit.getPatientId().equals(presc.getPatientId())
                        && visit.getDoctorId().equals(presc.getDoctorId())))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "No matching visit for prescription with id: [%s]".formatted(prescriptionDTO.getPrescriptionId())));
    }

//    public List<VisitDTO> getVisitsWithoutOpinionsForPatient(Integer patientId) {
//        return visitDAO.findByPatientId(patientId).stream()
//                .filter(visit -> visit.getOpinion() == null)
//                .toList();
//    }
}
