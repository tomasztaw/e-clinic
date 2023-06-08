package pl.taw.infrastructure.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.repository.jpa.DoctorJpaRepository;
import pl.taw.infrastructure.database.repository.jpa.PatientJpaRepository;

@Slf4j
@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

    private PatientJpaRepository patientJpaRepository;
    private DoctorJpaRepository doctorJpaRepository;

    @Override
    public void onApplicationEvent(final @NotNull ContextRefreshedEvent event) {
        /*doctorJpaRepository.deleteAll();
        patientJpaRepository.deleteAll();

        patientJpaRepository.save(PatientEntity.builder()
                .name("Stefan")
                .surname("Zajavka")
                .pesel("8506171835")
                .phone("+48 548 664 441")
                .email("zajavka@zajavka.com")
                .build());

        patientJpaRepository.save(PatientEntity.builder()
                .name("Agnieszka")
                .surname("Spring")
                .pesel("8506171834")
                .phone("+48 548 115 312")
                .email("age@zajavka.com")
                .build());

        patientJpaRepository.save(PatientEntity.builder()
                .name("Tomasz")
                .surname("Hibernate")
                .pesel("8506171837")
                .phone("+48 548 656 565")
                .email("hibertomasz@zajavka.com")
                .build());

        doctorJpaRepository.save(DoctorEntity.builder()
                .name("Kornel")
                .surname("Makuszyński")
                .title("Lekarz rodzinny")
                .phone("+48 123 456 789")
                .email("znanay@gmail.com")
                .build());

        doctorJpaRepository.save(DoctorEntity.builder()
                .name("Jadwiga")
                .surname("Kuszyńska")
                .title("Pediatra")
                .phone("+48 123 333 789")
                .email("jkuszynska@gmail.com")
                .build());

        doctorJpaRepository.save(DoctorEntity.builder()
                .name("Wacław")
                .surname("Piątkowski")
                .title("Gastrolog")
                .phone("+48 666 456 789")
                .email("wacek@gmail.com")
                .build());

        doctorJpaRepository.save(DoctorEntity.builder()
                .name("Aleksander")
                .surname("Newski")
                .title("Gastrolog")
                .phone("+48 666 456 111")
                .email("oleknew@gmail.com")
                .build());

        doctorJpaRepository.save(DoctorEntity.builder()
                .name("Urszula")
                .surname("Nowakowska")
                .title("Lekarz rodzinny")
                .phone("+48 999 456 789")
                .email("ulala@gmail.com")
                .build());
*/
    }
}
