package pl.taw.controller.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"name", "surname", "title"})
@ToString(of = {"doctorId", "name", "surname", "title"})
public class Doctor {

    Integer doctorId;
    String name;
    String surname;
    String title;
    String email;
    String phone;

}
