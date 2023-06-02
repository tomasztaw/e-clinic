package pl.taw.util;

import lombok.experimental.UtilityClass;
import pl.taw.controller.dto.PatientDTO;

@UtilityClass
public class DtoFixtures {

    public static PatientDTO somePatient1() {
        return PatientDTO.builder()
                .name("Agnieszka")
                .surname("Zajavkowa")
                .pesel("85061712345")
                .phone("+48 548 665 441")
                .email("aga@zajavka.com")
                .build();
    }

    public static PatientDTO somePatient2() {
        return PatientDTO.builder()
                .name("Tomasz")
                .surname("Bednarek")
                .pesel("85061712345")
                .phone("+48 548 200 488")
                .email("tomala@zajavka.com")
                .build();
    }

}
