package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@With
@Entity
@Builder
@ToString(of = {"prescriptionId", "doctor", "patient", "createdAt"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Integer prescriptionId;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id")
    private VisitEntity visit;
}
