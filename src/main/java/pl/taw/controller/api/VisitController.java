package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.taw.controller.business.VisitService;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.VisitDTO;

import java.util.List;

@Controller
@RequestMapping(VisitController.VISITS)
@AllArgsConstructor
public class VisitController {

    public static final String VISITS = "/visits";
    public static final String DOCTOR_ID = "/doctor/{doctorId}";
    public static final String PATIENT_ID = "/patient/{patientId}";

    private final VisitService visitService;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    @GetMapping
    public String visitsPage() {
        return "visits";
    }

    @GetMapping(DOCTOR_ID)
    public String showVisitsByDoctor(@PathVariable("doctorId") Integer doctorId, Model model) {
        List<VisitDTO> visits = visitService.getDoctorVisits(doctorId);
        DoctorDTO doctor = doctorDAO.findById(doctorId);

        model.addAttribute("visits", visits);
        model.addAttribute("doctor", doctor);

        return "doctor-visits";
    }

    @GetMapping(PATIENT_ID)
    public String showVisitsByPatient(@PathVariable("patientId") Integer patientId, Model model) {
        List<VisitDTO> visits = visitService.getPatientVisits(patientId);
        PatientDTO patient = patientDAO.findById(patientId);

        model.addAttribute("visits", visits);
        model.addAttribute("patient", patient);

        return "patient-visits";
    }


}
