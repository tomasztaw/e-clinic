package pl.taw.controller.domain;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"id", "doctorId", "day", "startTimeR", "occupation"})
@ToString(of = {"id", "doctorId", "day", "startTimeR", "occupation"})
public class Reservation {

    Integer id;
    Integer doctorId;
    Integer patientId;
    LocalDate day;
    LocalTime startTimeR;
    LocalTime endTimeR;
    Boolean occupation;

    // relacje
    DoctorEntity doctor;
    PatientEntity patient;

}
