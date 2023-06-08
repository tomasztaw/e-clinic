package pl.taw.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.taw.infrastructure.database.entity.PatientEntity;

import java.util.Optional;

@Repository
public interface PatientJpaRepository extends JpaRepository<PatientEntity, Integer> {

    @Override
    Optional<PatientEntity> findById(Integer patientId);

    Optional<PatientEntity> findByPesel(String pesel);

}
