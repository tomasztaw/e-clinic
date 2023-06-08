package pl.taw.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@With
@Entity
@Builder
@ToString(of = {"name", "surname", "title", "email"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "title")
    private String title;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VisitEntity> visits;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionEntity> opinions;

}
