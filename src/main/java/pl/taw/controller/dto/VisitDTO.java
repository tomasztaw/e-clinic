package pl.taw.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDTO {

    private Integer visit_id;
    private Integer doctor_id;
    private Integer patient_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    private String status;

}
