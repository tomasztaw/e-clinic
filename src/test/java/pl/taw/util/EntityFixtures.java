package pl.taw.util;

import lombok.experimental.UtilityClass;
import pl.taw.infrastructure.database.entity.PatientEntity;


@UtilityClass
public class EntityFixtures {

    public static PatientEntity somePatient1() {
        return PatientEntity.builder()
                .name("Agnieszka")
                .surname("Zajavkowa")
                .pesel("85061712345")
                .phone("+48 548 665 441")
                .email("aga@zajavka.com")
                .build();
    }

    public static PatientEntity somePatient2() {
        return PatientEntity.builder()
                .name("Remigiusz")
                .surname("Spring")
                .pesel("85061712345")
                .phone("+48 548 665 488")
                .email("remek@zajavka.com")
                .build();
    }

    public static PatientEntity somePatient3() {
        return PatientEntity.builder()
                .name("Mariusz")
                .surname("Hibernate")
                .pesel("85061712345")
                .phone("+48 548 111 488")
                .email("mario@zajavka.com")
                .build();
    }
}
