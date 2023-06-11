package pl.taw.controller.dto;

import lombok.*;
import pl.taw.infrastructure.database.entity.DoctorEntity;

import java.time.LocalTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorScheduleDTO {

    private Integer id;
    private Integer doctorId;
    private Integer dayOfWeek;
    private LocalTime startTimeDs;
    private LocalTime endTimeDs;

    // relacje
    private DoctorEntity doctor;

}
