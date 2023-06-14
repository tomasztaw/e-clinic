package pl.taw.controller.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.taw.controller.business.DoctorService;
import pl.taw.controller.business.WorkingHours;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.DoctorsDTO;
import pl.taw.controller.dto.mapper.DoctorMapper;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.repository.jpa.DoctorJpaRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(DoctorController.DOCTORS)
@AllArgsConstructor
public class DoctorController {

    public static final String DOCTORS = "/doctors";
    public static final String DOCTOR_ID = "/{doctorId}";
    public static final String DOCTOR_ID_JS = "/js/{doctorId}";
    public static final String DOCTOR_ID_XX = "/xx/{doctorId}";
    public static final String DOCTOR_UPDATE_TITLE = "/{doctorId}/title";

    public static final String SPECIALIZATION = "/specialization/{specialization}";
    public static final String SPECIALIZATIONS = "/specializations";
    public static final String OPINIONS = "/opinions";
    public static final String SCHEDULE = "/schedule/{doctorId}";


    private DoctorJpaRepository doctorJpaRepository;
    private DoctorMapper doctorMapper;

    // serwis do pobrania godzin pracy lekarza
    private DoctorService doctorService;

    // warstwa dostępu
    private DoctorDAO doctorDAO;

    // grafik lekarza
    @GetMapping(SCHEDULE)
    public String getDoctorSchedule(@PathVariable Integer doctorId, Model model) {
        DoctorDTO doctor = doctorDAO.findById(doctorId);
        List<WorkingHours> workingHoursList = doctorService.getWorkingHours(doctorId);
        model.addAttribute("workingHoursList", workingHoursList);
        model.addAttribute("doctor", doctor);
//        return "doctor-schedule";
        return "doctor-schedule-date";
    }

    // dodajemy panel dla lekarzy
    @GetMapping("/panel")
    public String panelLekarzy(Model model) {
        List<DoctorDTO> doctors = doctorDAO.findAll().stream().map(doctorMapper::map).toList();
        model.addAttribute("doctors", doctors);
        model.addAttribute("updateDoctor", new DoctorDTO());
        return "doctor-panel";
    }

    @PostMapping("/add")
    public String addDoctor(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email
    ) {
        DoctorEntity newDoctor = DoctorEntity.builder()
                .name(name)
                .surname(surname)
                .title(title)
                .phone(phone)
                .email(email)
                .build();
        doctorDAO.saveDoctor(newDoctor);
        return "redirect:/doctors/doctor-panel";
    }

    @PutMapping("/update")
    public String updateDoctor(
            @ModelAttribute("updateDoctor") DoctorDTO updateDoctor
    ) {
        DoctorEntity doctorEntity = doctorDAO.findEntityById(updateDoctor.getDoctorId());
        doctorEntity.setName(updateDoctor.getName());
        doctorEntity.setSurname(updateDoctor.getSurname());
        doctorEntity.setTitle(updateDoctor.getTitle());
        doctorEntity.setPhone(updateDoctor.getPhone());
        doctorEntity.setEmail(updateDoctor.getEmail());
        doctorDAO.saveDoctor(doctorEntity);
        return "redirect:/doctors/doctor-panel";
    }

    @DeleteMapping("/delete/{doctorId}")
    public String deleteDoctorById(@PathVariable Integer doctorId) {
        Optional<DoctorEntity> doctorForDelete = doctorJpaRepository.findById(doctorId);
        if (doctorForDelete.isPresent()) {
            doctorDAO.delete(doctorForDelete.get());
        } else {
            throw new EntityNotFoundException("Could not found doctor with id: [%s]".formatted(doctorId));
        }
        return "redirect:/doctors/panel";
    }





    // DZIAŁA !!!!
    @GetMapping(SPECIALIZATION)
    public String doctorsBySpecializationsView(@PathVariable String specialization, Model model) {
        model.addAttribute("doctors", doctorJpaRepository.findAll().stream()
                .filter(doctor -> specialization.equals(doctor.getTitle().toLowerCase()))
                .map(doctorMapper::map)
                .toList());
        model.addAttribute("specialization", specialization);
        return "doctors-specialization";
    }

    // DZIAŁA !!!
    @GetMapping(value = SPECIALIZATIONS)
    public String specializations(Model model) {
        List<String> specializations = doctorJpaRepository.findAll().stream()
                .map(DoctorEntity::getTitle)
                .distinct()
                .toList();

        model.addAttribute("specializations", specializations);
        return "specializations";
    }

    // DZIAŁA !!!
    @GetMapping("/show/{doctorId}")
    public String showDoctorDetails(
            @PathVariable Integer doctorId,
            Model model
    ) {
        DoctorEntity doctorEntity = doctorJpaRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, doctorId: [%s]".formatted(doctorId)));
        model.addAttribute("doctor", doctorEntity);

        List<WorkingHours> workingHours = doctorService.getWorkingHours(doctorId);

        // Przekaz godziny pracy lekarza do modelu
        model.addAttribute("workingHours", workingHours);

        return "doctor-view";
    }

    @GetMapping
    public String doctors(Model model) {
        model.addAttribute("doctors", doctorJpaRepository.findAll().stream()
                .map(doctorMapper::map)
                .toList());
        return "doctors_logo";
    }

    @GetMapping(value = DOCTOR_ID,
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public DoctorDTO doctorDetails(@PathVariable Integer doctorId) {
        return doctorJpaRepository.findById(doctorId)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, doctorId: [%s]".formatted(doctorId)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDTO> addDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO
    ) {
        DoctorEntity doctorEntity = DoctorEntity.builder()
                .name(doctorDTO.getName())
                .surname(doctorDTO.getSurname())
                .title(doctorDTO.getTitle())
                .phone(doctorDTO.getPhone())
                .email(doctorDTO.getEmail())
                .build();
        DoctorEntity created = doctorJpaRepository.save(doctorEntity);
        return ResponseEntity
                .created(URI.create(DOCTORS.concat("/%s").formatted(created.getDoctorId())))
                .build();
    }

    @PutMapping(DOCTOR_ID)
    @Transactional
    public ResponseEntity<?> updateDoctor(
            @PathVariable Integer doctorId,
            @Valid @RequestBody DoctorDTO doctorDTO
    ) {
        DoctorEntity existingDoctor = doctorJpaRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, doctorId: [%s]".formatted(doctorId)));
        existingDoctor.setName(doctorDTO.getName());
        existingDoctor.setSurname(doctorDTO.getSurname());
        existingDoctor.setTitle(doctorDTO.getTitle());
        existingDoctor.setPhone(doctorDTO.getPhone());
        existingDoctor.setEmail(doctorDTO.getEmail());
        doctorJpaRepository.save(existingDoctor);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(DOCTOR_UPDATE_TITLE)
    public ResponseEntity<?> updateTitle(
            @PathVariable Integer doctorId,
            @RequestParam(defaultValue = "doctor") String newTitle
    ) {
        DoctorEntity existingDoctor = doctorJpaRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, doctorId: [%s]".formatted(doctorId)));
        existingDoctor.setTitle(newTitle);
        doctorJpaRepository.save(existingDoctor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(DOCTOR_ID)
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer doctorId) {
        Optional<DoctorEntity> doctorOpt = doctorJpaRepository.findById(doctorId);
        if (doctorOpt.isPresent()) {
            doctorJpaRepository.deleteById(doctorId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // jakieś głupoty

    @GetMapping(value = DOCTOR_ID_XX,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String doctorDetails(@PathVariable Integer doctorId, Model model) {
        DoctorDTO doctor = doctorJpaRepository.findById(doctorId)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, doctorId: [%s]".formatted(doctorId)));
        model.addAttribute("doctor", doctor);
        return "doctor-details";
    }

    @GetMapping(value = DOCTOR_ID_JS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DoctorDTO doctorDetailsJS(@PathVariable Integer doctorId) {
        return doctorJpaRepository.findById(doctorId)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, doctorId: [%s]".formatted(doctorId)));
    }

    @GetMapping("/doctors/specialization/{specialization}")
    public String doctorsBySpecializationsView_2(@PathVariable("specialization") String specialization, Model model) {
        List<DoctorDTO> doctors = doctorJpaRepository.findAll().stream()
                .filter(doctor -> specialization.equalsIgnoreCase(doctor.getTitle()))
                .map(doctorMapper::map)
                .collect(Collectors.toList());
        model.addAttribute("doctors", doctors);
        model.addAttribute("specialization", specialization);
        return "doctors-s";
    }

}
