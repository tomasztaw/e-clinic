package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.VisitDTO;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VisitService {

    private final VisitDAO visitDAO;

    public List<VisitDTO> getDoctorVisits(Integer doctorId) {
        return visitDAO.findByDoctorId(doctorId);
    }

    public List<VisitDTO> getPatientVisits(Integer patientId) {
        return visitDAO.findByPatientId(patientId);
    }

    public List<VisitDTO> getVisitsWithoutOpinionsForPatient(Integer patientId) {
        return visitDAO.findByPatientId(patientId).stream()
                .filter(visit -> visit.getOpinion() == null)
                .toList();
    }
}
