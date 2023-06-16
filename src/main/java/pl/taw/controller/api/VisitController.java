package pl.taw.controller.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.taw.controller.business.PrescriptionService;
import pl.taw.controller.business.VisitService;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(VisitController.VISITS)
@AllArgsConstructor
public class VisitController {

    public static final String VISITS = "/visits";
    public static final String DOCTOR_ID = "/doctor/{doctorId}";
    public static final String PATIENT_ID = "/patient/{patientId}";
    public static final String PANEL = "/panel";
    public static final String SHOW = "/show/{visitId}";
    public static final String ADD = "/add";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete/{visitId}";

    private final VisitService visitService;
    private final PrescriptionService prescriptionService;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;
    private final VisitDAO visitDAO;

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

        model.addAttribute("visits", visits);

        return "patient-visits";
    }

    // panel
    @GetMapping(PANEL)
    public String showVisitPanel(Model model) {
        List<VisitDTO> visits = visitDAO.findAll();
        model.addAttribute("visits", visits);
        model.addAttribute("updateVisit", new VisitDTO());
        return "visit-panel";
    }

    @GetMapping(SHOW)
    public String showVisit(@PathVariable("visitId") Integer visitId, Model model) {
        VisitDTO visit = visitDAO.findDTOById(visitId);
        model.addAttribute("visit", visit);
        // dodawanie modelu z receptą znalezioną na podstawie daty i identyfikatorów
        PrescriptionDTO prescription = prescriptionService.findPrescriptionFromVisit(visit);
        model.addAttribute("prescription", prescription);
        return "visit-view";
    }

    @PostMapping(ADD)
    public String addVisit(
            @RequestParam(value = "doctorId") Integer doctorId,
            @RequestParam(value = "patientId") Integer patientId,
            @RequestParam(value = "startTime")LocalDateTime startTime,
            @RequestParam(value = "note") String note,
            @RequestParam(value = "status") String status
    ) {
        VisitEntity newVisit = VisitEntity.builder()
                .doctorId(doctorId)
                .patientId(patientId)
                .startTime(LocalDateTime.now())
                .endTime(startTime.plusMinutes(10))
                .note(note)
                .status(status)
                .build();
        visitDAO.saveVisit(newVisit);
        return "redirect:/visits/visit-panel";
    }

    @PutMapping(UPDATE)
    public String updateVisit(
            @ModelAttribute("updateVisit") VisitDTO updateVisit
    ) {
        VisitEntity visitEntity = visitDAO.findById(updateVisit.getVisitId());
        visitEntity.setVisitId(updateVisit.getVisitId());
        visitEntity.setDoctorId(updateVisit.getDoctorId());
        visitEntity.setPatientId(updateVisit.getPatientId());
        visitEntity.setStartTime(updateVisit.getStartTime());
        visitEntity.setEndTime(updateVisit.getStartTime().plusMinutes(10));
        visitEntity.setNote(updateVisit.getNote());
        visitEntity.setStatus(updateVisit.getStatus());
        visitDAO.saveVisit(visitEntity);
        // jeżeli dodaje wizytę, to jednocześnie powinienem wystawić receptę,
        // czyli powinno mnie przekierować do wystawiania recept ????
        // TODO przekierowanie do recep
        return "redirect:/visits/visit-panel";
    }

    @DeleteMapping(DELETE)
    public String deleteVisitById(@PathVariable Integer visitId) {
        VisitEntity visitForDelete = visitDAO.findById(visitId);
        visitDAO.delete(visitForDelete);
        return "redirect:/visits/visit-panel";
    }

}
