package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.taw.controller.business.OpinionService;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.domain.Opinion;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.OpinionEntity;

import java.util.List;

@Controller
@RequestMapping(OpinionController.OPINIONS)
@AllArgsConstructor
public class OpinionController {

    public static final String OPINIONS = "/opinions";
    public static final String DOCTOR_ID = "/doctor/{doctorId}";
    public static final String PATIENT_ID = "/patient/{patientId}";


    private final OpinionService opinionService;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;


    @GetMapping
    public String opinionsPage() {
        return "opinions";
    }

    // wyświetlanie opinii lekarza
    @GetMapping(DOCTOR_ID)
    public String showDoctorOpinions(@PathVariable("doctorId") Integer doctorId, Model model) {
        List<OpinionDTO> opinions = opinionService.getDoctorOpinions(doctorId);
        DoctorDTO doctor = doctorDAO.findById(doctorId);

        model.addAttribute("opinions", opinions);
        model.addAttribute("doctor", doctor);

        return "doctor-opinions"; // Nazwa widoku, do którego zostaną przekazane opinie.
    }

    // wyświetlanie opinii konkretnego pacjenta
    @GetMapping(PATIENT_ID)
    public String showPatientOpinions(@PathVariable("patientId") Integer patientId, Model model) {
        List<OpinionDTO> opinions = opinionService.getPatientOpinions(patientId);
        PatientDTO patient = patientDAO.findById(patientId);

        model.addAttribute("opinions", opinions);
        model.addAttribute("patient", patient);

        return "patient-opinions";
    }


}
