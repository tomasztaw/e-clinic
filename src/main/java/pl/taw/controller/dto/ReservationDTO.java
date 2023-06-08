package pl.taw.controller.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Integer id;
    private Integer doctorId;
    private LocalDate day;
    private LocalTime startTimeR;
    private LocalTime endTimeR;
    private Boolean occupied;

}
