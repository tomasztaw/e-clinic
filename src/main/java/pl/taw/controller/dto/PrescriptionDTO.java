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
//    private Integer doctorId;
    private DoctorEntity doctor;
//    private Integer patientId;
    private PatientEntity patient;
    private String medicationName;
    private String dosage;
    private String instructions;
    private LocalDateTime createdAt;
}
