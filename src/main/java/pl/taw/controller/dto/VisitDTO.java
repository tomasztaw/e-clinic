package pl.taw.controller.dto;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.OpinionEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDTO {

    private Integer visitId;
    private Integer doctor_id;
    private Integer patient_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    private String status;

    // relacje
    private DoctorEntity doctor;
    private PatientEntity patient;
    private OpinionEntity opinion;

}
