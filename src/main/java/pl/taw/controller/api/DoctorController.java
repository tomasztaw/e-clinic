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
import pl.taw.controller.domain.Doctor;
import pl.taw.controller.dto.DoctorDTO;
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
    public static final String DOCTOR_ID = "/{id}";
    public static final String DOCTOR_ID_JS = "/js/{id}";
    public static final String DOCTOR_ID_XX = "/xx/{id}";
    public static final String DOCTOR_UPDATE_TITLE = "/{id}/title";

    public static final String SPECIALIZATION = "/specialization/{specialization}";
    //    public static final String SPECIALIZATION = "/{specialization}";
    public static final String SPECIALIZATIONS = "/specializations";


    private DoctorJpaRepository doctorJpaRepository;
    private DoctorMapper doctorMapper;


    @GetMapping(SPECIALIZATION)
    public String doctorsBySpecializationsView(@PathVariable String specialization, Model model) {
        model.addAttribute("doctors", doctorJpaRepository.findAll().stream()
                .filter(doctor -> specialization.equals(doctor.getTitle().toLowerCase()))
                .map(doctorMapper::map)
                .toList());
        model.addAttribute("specialization", specialization);
        return "doctors-s";
    }

    @GetMapping(value = SPECIALIZATIONS)
    public String specializations(Model model) {
        List<String> specializations = doctorJpaRepository.findAll().stream()
                .map(DoctorEntity::getTitle)
                .distinct()
                .toList();

        model.addAttribute("specializations", specializations);
        return "specializations";
    }

    @GetMapping("/show/{id}")
    public String showDoctorDetails(
            @PathVariable Integer id,
            Model model
    ) {
        DoctorEntity doctorEntity = doctorJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
        model.addAttribute("doctor", doctorEntity);
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
    public DoctorDTO doctorDetails(@PathVariable Integer id) {
        return doctorJpaRepository.findById(id)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
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
                .created(URI.create(DOCTORS.concat("/%s").formatted(created.getId())))
                .build();
    }

    @PutMapping(DOCTOR_ID)
    @Transactional
    public ResponseEntity<?> updateDoctor(
            @PathVariable Integer id,
            @Valid @RequestBody DoctorDTO doctorDTO
    ) {
        DoctorEntity existingDoctor = doctorJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
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
            @PathVariable Integer id,
            @RequestParam(defaultValue = "doctor") String newTitle
    ) {
        DoctorEntity existingDoctor = doctorJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
        existingDoctor.setTitle(newTitle);
        doctorJpaRepository.save(existingDoctor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(DOCTOR_ID)
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer id) {
        Optional<DoctorEntity> doctorOpt = doctorJpaRepository.findById(id);
        if (doctorOpt.isPresent()) {
            doctorJpaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // jakieś głupoty

    @GetMapping(value = DOCTOR_ID_XX,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String doctorDetails(@PathVariable Integer id, Model model) {
        DoctorDTO doctor = doctorJpaRepository.findById(id)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
        model.addAttribute("doctor", doctor);
        return "doctor-details";
    }

    @GetMapping(value = DOCTOR_ID_JS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DoctorDTO doctorDetailsJS(@PathVariable Integer id) {
        return doctorJpaRepository.findById(id)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
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
