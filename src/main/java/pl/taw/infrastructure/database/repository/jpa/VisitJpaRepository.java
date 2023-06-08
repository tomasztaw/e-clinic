package pl.taw.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.util.List;

@Repository
public interface VisitJpaRepository extends JpaRepository<VisitEntity, Integer> {


    // wyrzuca błąd przy uruchamianiu aplikacji !!!
//    List<VisitEntity> findAllByDoctorId(Integer doctorId);
//
//    List<VisitEntity> findAllByPatientId(Integer patientId);

}
