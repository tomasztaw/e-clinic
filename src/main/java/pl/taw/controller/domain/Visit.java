package pl.taw.controller.domain;

import lombok.*;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"visitId", "note", "status"})
@ToString(of = {"visitId", "doctor", "patient", "startTime", "note", "status"})
public class Visit {

    Integer visitId;
    Doctor doctor;
    Patient patient;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String note;
    String status;

}
