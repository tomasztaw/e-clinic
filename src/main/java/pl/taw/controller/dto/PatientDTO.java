package pl.taw.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.taw.infrastructure.database.entity.OpinionEntity;
import pl.taw.infrastructure.database.entity.ReservationEntity;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.util.List;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Integer patientId;
    private String name;
    private String surname;
    private String pesel;
    @Size(min = 7, max = 15)
    @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
    private String phone;
    @Email
    private String email;

    // relacje
    private List<VisitEntity> visits;
    private List<OpinionEntity> createdOpinions;
    private List<ReservationEntity> reservations;

}
