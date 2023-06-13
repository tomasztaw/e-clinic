package pl.taw.controller.domain;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"opinionId", "doctorId", "patientId"})
@ToString(of = {"opinionId", "doctorId", "patientId", "comment"})
public class Opinion {

    Integer opinionId;
    Integer doctorId;
    Integer patientId;
    String comment;
    LocalDateTime createdAt;

    // relacje
    DoctorEntity doctor;
    PatientEntity patient;
    VisitEntity visit;

}
