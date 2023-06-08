package pl.taw.controller.dto;

import lombok.*;

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
    private String instruction;
    private LocalDateTime createdAt;
}
