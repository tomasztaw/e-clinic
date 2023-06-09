package pl.taw.controller.domain;

import lombok.*;

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

}
