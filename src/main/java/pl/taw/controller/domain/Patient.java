package pl.taw.controller.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "pesel")
@ToString(of = {"id", "name", "surname", "pesel"})
public class Patient {

    Integer id;
    String name;
    String surname;
    String pesel;
    String email;
    String phone;

}