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
public class VisitDTO {

    private Integer visit_id;
//    private Integer doctor_id;
    private DoctorEntity doctor;
//    private Integer patient_id;
    private PatientEntity patient;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    private String status;

}
