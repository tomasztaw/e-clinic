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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Powiązanie opinii z wizytą (opinia może mieć tylko jedną lub zero wizyt(ę))
    @OneToOne
    @JoinColumn(name = "visit_id")
    private VisitEntity visit;

}
