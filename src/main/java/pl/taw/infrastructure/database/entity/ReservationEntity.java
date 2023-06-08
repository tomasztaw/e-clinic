package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@With
@Entity
@Builder
@ToString(of = {})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "start_time_r")
    private LocalTime startTimeR;

    @Column(name = "end_time_r")
    private LocalTime endTimeR;

    @Column(name = "occupied")
    private Boolean occupied;

}
