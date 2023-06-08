package pl.taw.controller.dto;

import lombok.*;

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

}
