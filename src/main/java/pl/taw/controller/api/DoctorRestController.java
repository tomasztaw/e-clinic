package pl.taw.controller.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.taw.controller.business.DoctorService;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.mapper.DoctorMapper;
import pl.taw.infrastructure.database.repository.jpa.DoctorJpaRepository;

import java.util.List;

@RestController
@RequestMapping(DoctorRestController.API_DOCTORS)
@AllArgsConstructor
public class DoctorRestController {

    public static final String API_DOCTORS = "/api/doctors";
    public static final String DOCTOR_ID = "/{doctorId}";
    public static final String SPECIALIZATION = "/specialization/{specialization}";
    public static final String SPECIALIZATIONS = "/specializations";

    private final DoctorJpaRepository doctorJpaRepository; // do pobrania z bazy
    private final DoctorMapper doctorMapper; // do przekształcenia na DTOsy

    private final DoctorService doctorService; // nie wiem czy to nie nadmiarowe!!!

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorDTO> doctorsApiList() {
        return doctorJpaRepository.findAll().stream()
                .map(doctorMapper::map)
                .toList();
    }

    @GetMapping(value = DOCTOR_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorDTO doctorDTO(@PathVariable Integer id) {
        return doctorJpaRepository.findById(id)
                .map(doctorMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(
                        "DoctorEntity not found, id: [%s]".formatted(id)));
    }

    @GetMapping(value = SPECIALIZATION, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorDTO> getDoctorsBySpecialization(@PathVariable String specialization) {
        return doctorService.getDoctorsBySpecialization(specialization.toLowerCase());
    }

    @GetMapping(value = SPECIALIZATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getDoctorSpecializations() {
        return doctorService.getDoctorSpecializations();
    }


}
