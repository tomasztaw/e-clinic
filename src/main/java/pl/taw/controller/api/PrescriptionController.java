package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.taw.controller.business.PrescriptionService;
import pl.taw.controller.business.VisitService;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.dto.*;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(PrescriptionController.PRESCRIPTIONS)
@AllArgsConstructor
public class PrescriptionController {

    public static final String PRESCRIPTIONS = "/prescriptions";
    public static final String DOCTOR_ID = "/doctor/{doctorId}";
    public static final String PATIENT_ID = "/patient/{patientId}";

    private final PrescriptionService prescriptionService;
    private final VisitService visitService;
    private final PrescriptionDAO prescriptionDAO;
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

    // panel z receptami
    @GetMapping("/panel")
    public String showPrescriptionPanel(Model model) {
        List<PrescriptionDTO> prescriptions = prescriptionDAO.findAll();
        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("updatePrescription", new PrescriptionDTO());
        return "prescription-panel";
    }

    @GetMapping("/show/{prescriptionId}")
    public String showPrescription(@PathVariable("prescriptionId") Integer prescriptionId, Model model) {
        PrescriptionDTO prescription = prescriptionDAO.findDTOById(prescriptionId);
        model.addAttribute("prescription", prescription);

        // dodawanie modelu z wizytą znalezioną na podstawie id powiązanego z receptą
//        VisitDTO visit = prescriptionDAO.findVisitForPrescription(prescription.getVisit().getVisitId());
//        model.addAttribute("visit", visit);

        // TODO do zrobienia widok
        return "prescription-view";
    }

    @PostMapping("/add")
    public String addPrescription(
            @RequestParam(value = "doctorId") Integer doctorId,
            @RequestParam(value = "patientId") Integer patientId,
            @RequestParam(value = "medicationName") String medicationName,
            @RequestParam(value = "dosage") String dosage,
            @RequestParam(value = "instructions") String instructions,
            @RequestParam(value = "createAt") LocalDateTime createAt
    ) {
        PrescriptionEntity newPrescription = PrescriptionEntity.builder()
                .doctorId(doctorId)
                .patientId(patientId)
                .medicationName(medicationName)
                .dosage(dosage)
                .instructions(instructions)
                .createdAt(createAt)
                .build();
        prescriptionDAO.savePrescription(newPrescription);
        // czy stworzenie recepty na podstawie wizyty ma przekierować na wizyty, czy na recepty?
        return "redirect:/prescriptions/prescription-panel";
    }

    @PutMapping("/update")
    public String updatePrescription(
            @ModelAttribute("updatePrescription") PrescriptionDTO updatePrescription
    ) {
        PrescriptionEntity prescriptionEntity = prescriptionDAO.findById(updatePrescription.getPrescriptionId());
        prescriptionEntity.setPrescriptionId(updatePrescription.getPrescriptionId());
        prescriptionEntity.setDoctorId(updatePrescription.getDoctorId());
        prescriptionEntity.setPatientId(updatePrescription.getPatientId());
        prescriptionEntity.setMedicationName(updatePrescription.getMedicationName());
        prescriptionEntity.setDosage(updatePrescription.getDosage());
        prescriptionEntity.setInstructions(updatePrescription.getInstructions());
        prescriptionEntity.setCreatedAt(updatePrescription.getCreatedAt());
        prescriptionDAO.savePrescription(prescriptionEntity);
        return "redirect:/prescriptions/prescription-panel";
    }

    @DeleteMapping("/delete/{prescriptionId}")
    public String deletePrescriptionById(@PathVariable Integer prescriptionId) {
        PrescriptionEntity prescriptionForDelete = prescriptionDAO.findById(prescriptionId);
        prescriptionDAO.delete(prescriptionForDelete);
        return "redirect:/prescriptions/prescription-panel";
    }

}
