package pl.taw.controller.domain;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorScheduleEntity;
import pl.taw.infrastructure.database.entity.OpinionEntity;
import pl.taw.infrastructure.database.entity.ReservationEntity;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"name", "surname", "title"})
@ToString(of = {"doctorId", "name", "surname", "title"})
public class Doctor {

    Integer doctorId;
    String name;
    String surname;
    String title;
    String email;
    String phone;

    // relacje
    List<VisitEntity> visits;
    List<OpinionEntity> opinions;
    List<DoctorScheduleEntity> schedules;
    List<ReservationEntity> reservations;

}
