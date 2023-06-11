package pl.taw.controller.domain;

import lombok.*;
import pl.taw.infrastructure.database.entity.OpinionEntity;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "pesel")
@ToString(of = {"patientId", "name", "surname", "pesel"})
public class Patient {

    Integer patientId;
    String name;
    String surname;
    String pesel;
    String email;
    String phone;

    // relacje
    List<VisitEntity> visits;
    List<OpinionEntity> createdOpinions;
}
