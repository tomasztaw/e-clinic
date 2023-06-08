package pl.taw.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpinionDTO {

    private Integer opinionId;
    private Integer doctorId;
    private Integer patientId;
    private String comment;
    private LocalDateTime createdAt;

}
