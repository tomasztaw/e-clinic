package pl.taw.controller.domain;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"prescriptionId", "doctorId", "patientId"})
@ToString(of = {"prescriptionId", "doctorId", "patientId", "createdAt"})
public class Prescription {

    Integer prescriptionId;
    Integer doctorId;
    Integer patientId;
    String medicationName;
    String dosage;
    String instructions;
    LocalDateTime createdAt;

    // relacje
    DoctorEntity doctor;
    PatientEntity patient;
}
