package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@With
@Entity
@Builder
@ToString(of = {"visitId", "doctor", "patient", "startTime", "note", "status"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visits")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer visitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    // Powiązanie wizyt z opiniami (wizyta może mieć jedną opinię dodaną po czasie, dlatego na początku zawsze jest pusta)
    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
    private OpinionEntity opinion;

}
