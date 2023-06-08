package pl.taw.controller.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.PatientsDTO;
import pl.taw.controller.dto.VisitsDTO;
import pl.taw.controller.dto.mapper.PatientMapper;
import pl.taw.controller.dto.mapper.VisitMapper;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.repository.VisitRepository;
import pl.taw.infrastructure.database.repository.jpa.PatientJpaRepository;
import pl.taw.infrastructure.database.repository.jpa.VisitJpaRepository;

import java.net.URI;

@Controller
@RequestMapping(PatientController.PATIENTS)
@AllArgsConstructor
public class PatientController {

    public static final String PATIENTS = "/patients";
    public static final String LOGIN = "/login";
    public static final String DASHBOARD = "/dashboard";
    public static final String PATIENT_ID = "/{patientId}";
    public static final String PATIENT_UPDATE_PHONE = "/{patientId}/phone";
    public static final String PATIENT_UPDATE_PHONE_VIEW = "/phone-view";
    public static final String PATIENT_ID_RESULT = "/%s";
    public static final String HISTORY = "/history";


    private final PatientJpaRepository patientJpaRepository;
    private final PatientMapper patientMapper;
    // dodanie wizyt
    private final VisitRepository visitRepository;
    private final VisitJpaRepository visitJpaRepository;
    private final VisitMapper visitMapper;

    @GetMapping(LOGIN)
    public String showLoginForm() {
        return "login";
    }


    @PostMapping(LOGIN)
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equals("user") && password.equals("test")) {
//            return "redirect:/clinic/patients/dashboard";
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
//            return "redirect:/clinic/patients/login";
            return "redirect:/login";
        }
    }

    @GetMapping(DASHBOARD)
    public String showDashboard(Model model) {
        String pesel = "8506171837";
        PatientDTO patientDTO = patientJpaRepository.findByPesel(pesel)
                .map(patientMapper::map)
                .orElseThrow();

        model.addAttribute("patient", patientDTO);

        return "patient-dashboard";
    }

    @GetMapping(HISTORY)
    public String showHistory(Model model) {
        String pesel = "8506171837";
        PatientDTO patientDTO = patientJpaRepository.findByPesel(pesel)
                .map(patientMapper::map)
                .orElseThrow();

        VisitsDTO history = VisitsDTO.of(visitJpaRepository.findAll().stream()
                .filter(visit -> visit.getPatient().getPatientId().equals(patientDTO.getPatientId()))
                .map(visitMapper::map)
                .toList());

        model.addAttribute("patient", patientDTO);
        model.addAttribute("history", history);
        return "history";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PatientsDTO patientsList() {
        return PatientsDTO.of(patientJpaRepository.findAll().stream()
                .map(patientMapper::map)
                .toList());
    }

    @GetMapping(value = PATIENT_ID,
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public PatientDTO patientDetails(@PathVariable Integer id) {
        return patientJpaRepository.findById(id)
                .map(patientMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PatientEntity not found, id: [%s]".formatted(id)
                ));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDTO> addPatient(
            @Valid @RequestBody PatientDTO patientDTO
    ) {
        PatientEntity patientEntity = PatientEntity.builder()
                .name(patientDTO.getName())
                .surname(patientDTO.getSurname())
                .pesel(patientDTO.getPesel())
                .phone(patientDTO.getPhone())
                .email(patientDTO.getEmail())
                .build();
        PatientEntity created = patientJpaRepository.save(patientEntity);
        return ResponseEntity
                .created(URI.create(PATIENTS + PATIENT_ID_RESULT.formatted(created.getPatientId())))
                .build();
    }

    @PutMapping(PATIENT_ID)
    @Transactional
    public ResponseEntity<?> updatePatient(
            @PathVariable Integer id,
            @Valid @RequestBody PatientDTO patientDTO
    ) {
        PatientEntity existingPatient = patientJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PatientEntity not found, id: [%s]".formatted(id)));
        existingPatient.setName(patientDTO.getName());
        existingPatient.setSurname(patientDTO.getSurname());
        existingPatient.setPesel(patientDTO.getPesel());
        existingPatient.setPhone(patientDTO.getPhone());
        existingPatient.setEmail(patientDTO.getEmail());
        patientJpaRepository.save(existingPatient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(PATIENT_ID)
    public ResponseEntity<?> deletePatient(
            @PathVariable Integer id
    ) {
        var patientOpt = patientJpaRepository.findById(id);
        if (patientOpt.isPresent()) {
            patientJpaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // dodanie dla widoku aktualizacji telefonu
    @GetMapping(PATIENT_UPDATE_PHONE_VIEW)
    public String showUpdatePhoneView(Model model) {
        String pesel = "8506171837";
        PatientDTO patientDTO = patientJpaRepository.findByPesel(pesel)
                .map(patientMapper::map)
                .orElseThrow();

        model.addAttribute("patient", patientDTO);
        return "update-phone-view";
    }

    @PatchMapping(PATIENT_UPDATE_PHONE)
    public ResponseEntity<?> updatePatientPhone(
            @PathVariable Integer id,
            @RequestParam(required = true) String newPhone
    ) {
        PatientEntity existingPatient = patientJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PatientEntity not found, id: [%s]".formatted(id)));
        existingPatient.setPhone(newPhone);
        patientJpaRepository.save(existingPatient);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "test-header")
    public ResponseEntity<?> testHeader(
            @RequestHeader(value = HttpHeaders.ACCEPT) MediaType accept,
            @RequestHeader(value = "httpStatus", required = true) int httpStatus
    ) {
        return ResponseEntity
                .status(httpStatus)
                .header("x-my-header", accept.toString())
                .body("Accepted: " + accept);
    }

}
