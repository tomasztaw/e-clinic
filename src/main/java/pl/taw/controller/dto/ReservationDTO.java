package pl.taw.controller.dto;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Integer id;
    private Integer doctorId;
    private Integer patientId;
    private LocalDate day;
    private LocalTime startTimeR;
    private LocalTime endTimeR;
    private Boolean occupied;

    // relacje
    private DoctorEntity doctor;
    private PatientEntity patient;
}
