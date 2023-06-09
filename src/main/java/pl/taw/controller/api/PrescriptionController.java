package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.taw.controller.business.PrescriptionService;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.PrescriptionDTO;

import java.util.List;

@Controller
@RequestMapping(PrescriptionController.PRESCRIPTIONS)
@AllArgsConstructor
public class PrescriptionController {

    public static final String PRESCRIPTIONS = "/prescriptions";
    public static final String DOCTOR_ID = "/doctor/{doctorId}";
    public static final String PATIENT_ID = "/patient/{patientId}";

    private final PrescriptionService prescriptionService;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    @GetMapping
    public String prescriptionPage() {
        return "prescriptions";
    }

    // wyświetlanie recept wystawionych przez danego lekarza
    @GetMapping(DOCTOR_ID)
    public String showDoctorPrescriptions(@PathVariable("doctorId") Integer doctorId, Model model) {
        List<PrescriptionDTO> prescriptions = prescriptionService.getDoctorPrescriptions(doctorId);
        DoctorDTO doctor = doctorDAO.findById(doctorId);

        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("doctor", doctor);

        return "doctor-prescriptions";
    }

    @GetMapping(PATIENT_ID)
    public String showPatientPrescriptions(@PathVariable("patientId") Integer patientId, Model model) {
        List<PrescriptionDTO> prescriptions = prescriptionService.getPatientPrescriptions(patientId);
        PatientDTO patient = patientDAO.findById(patientId);

        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("patient", patient);

        return "patient-prescriptions";
    }
}
