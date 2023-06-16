package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@With
@Entity
@Builder
@ToString(of = {"id", "doctorId", "day", "startTimeR", "occupied"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "doctor_id")
    private Integer doctorId;

    //dodanie pacjenta
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "start_time_r")
    private LocalTime startTimeR;

    @Column(name = "end_time_r")
    private LocalTime endTimeR;

    @Column(name = "occupied")
    private Boolean occupied;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;


}
