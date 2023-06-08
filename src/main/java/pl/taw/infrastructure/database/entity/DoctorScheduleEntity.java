package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@With
@Entity
@Builder
@ToString(of = {"id", "doctor", "dayOfWeek", "startTimeDs"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors_schedule")
public class DoctorScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "start_time_ds")
    private LocalTime startTimeDs;

    @Column(name = "end_time_ds")
    private LocalTime endTimeDs;

}
