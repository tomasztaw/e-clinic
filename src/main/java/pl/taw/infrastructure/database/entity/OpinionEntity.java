package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@With
@Entity
@Builder
@ToString(of = {"opinionId", "doctor", "patient", "comment", "createdAt"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "opinions")
public class OpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Integer opinionId;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "patient_id")
    private Integer patientId;

//    @Column(name = "visit_id_visit") //, insertable = false, updatable = false) - nie zapisywało tego pola do bazy
//    private Integer visitIdVisit;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // relacje
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;

    // id wizyty na początku zawsze będzie null, dlatego nie ustawiłem jako klucz obcy
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id")
    private VisitEntity visit;



}
