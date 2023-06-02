package pl.taw.infrastructure.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.taw.infrastructure.database.entity.DoctorEntity;
import pl.taw.infrastructure.database.entity.PatientEntity;
import pl.taw.infrastructure.database.repository.DoctorRepository;
import pl.taw.infrastructure.database.repository.PatientRepository;

@Slf4j
@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    @Override
    public void onApplicationEvent(final @NotNull ContextRefreshedEvent event) {
        doctorRepository.deleteAll();
        patientRepository.deleteAll();

        patientRepository.save(PatientEntity.builder()
                .name("Stefan")
                .surname("Zajavka")
                .pesel("8506171835")
                .phone("+48 548 664 441")
                .email("zajavka@zajavka.com")
                .build());

        patientRepository.save(PatientEntity.builder()
                .name("Agnieszka")
                .surname("Spring")
                .pesel("8506171834")
                .phone("+48 548 115 312")
                .email("age@zajavka.com")
                .build());

        patientRepository.save(PatientEntity.builder()
                .name("Tomasz")
                .surname("Hibernate")
                .pesel("8506171837")
                .phone("+48 548 656 565")
                .email("hibertomasz@zajavka.com")
                .build());

        doctorRepository.save(DoctorEntity.builder()
                .name("Doktor1")
                .surname("Nazwisko1")
                .title("Lekarzyna")
                .phone("+48 123 456 789")
                .email("znanay@gmail.com")
                .build());
    }
}