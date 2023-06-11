package pl.taw.controller.dto;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.time.LocalDateTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {

    private Integer prescriptionId;
    private Integer doctorId;
    private Integer patientId;
    private String medicationName;
    private String dosage;
    private String instructions;
    private LocalDateTime createdAt;

    // relacje
    private DoctorEntity doctor;
    private PatientEntity patient;

}
