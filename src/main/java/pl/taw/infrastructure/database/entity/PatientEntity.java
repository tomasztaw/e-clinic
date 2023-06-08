package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@With
@Entity
@Builder
@ToString(of = {"name", "surname", "pesel", "email"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class PatientEntity {

    @Id // info dla ORM, że to pole będzie używane jako unikalny identyfikator encji
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generowanie unikalnej wartości podczas wstawiania nowego rekordu do bazy
    @Column(name = "patient_id") // nazwa tabeli w bazie danych dla mappowanie, może się różnić np: userId -> user_id
    private Integer patientId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VisitEntity> visits;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionEntity> createdOpinions;

}
