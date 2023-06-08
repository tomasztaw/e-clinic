package pl.taw.controller.domain;

import lombok.*;

import java.time.LocalTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"id", "doctorId", "dayOfWeek", "startTimeDs"})
@ToString(of = {"id", "doctorId", "dayOfWeek", "startTimeDs"})
public class DoctorSchedule {

    Integer id;
    Integer doctorId;
    Integer dayOfWeek;
    LocalTime startTimeDs;
    LocalTime endTimeDs;

}
