package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@With
@Entity
@Builder
@ToString(of = {"visitId", "doctorId", "patientId", "startTime", "note", "status"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visits")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer visitId;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;

    @OneToOne(mappedBy = "visit")
    private OpinionEntity opinion;


    // recepta powinna być do każdej wizyt (można pomyśleć nad @MapId, ale nie chcę komplikować)
    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PrescriptionEntity prescription;
}
