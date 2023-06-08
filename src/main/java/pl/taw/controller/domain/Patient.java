package pl.taw.controller.domain;

import lombok.*;

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

}
