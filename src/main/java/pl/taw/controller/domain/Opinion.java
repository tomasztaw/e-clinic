package pl.taw.controller.domain;

import lombok.*;

import java.time.LocalDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"opinionId", "doctorId", "patientId"})
@ToString(of = {"opinionId", "doctorId", "patientId", "comment"})
public class Opinion {

    // Możliwe, że w przyszłości będzie potrzebne zmienić pola doctorId i patientId na obiekty Encji lub DTO
    Integer opinionId;
    Integer doctorId; // !!!
    Integer patientId; // !!!
    String comment;
    LocalDateTime createdAt;

    Visit visit;

}
