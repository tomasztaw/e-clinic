package pl.taw.controller.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"visitId", "note", "status"})
@ToString(of = {"visitId", "doctorId", "patientId", "startTime", "note", "status"})
public class Visit {

    Integer visitId;
    Integer doctorId;
    Integer patientId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String note;
    String status;

    // relacje
    Doctor doctor;
    Patient patient;
    Opinion opinion;

}
