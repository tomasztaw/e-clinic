package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.VisitEntity;
import pl.taw.infrastructure.database.repository.jpa.VisitJpaRepository;
import pl.taw.infrastructure.database.repository.mapper.VisitEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class VisitRepository implements VisitDAO {

    private final VisitJpaRepository visitJpaRepository;
    private final VisitEntityMapper visitEntityMapper;

    @Override
    public List<VisitDTO> findByStatus(String status) {
        return visitJpaRepository.findAll().stream()
                .filter(visit -> status.equals(visit.getStatus()))
                .map(visitEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public VisitEntity findById(Integer visitId) {
        return visitJpaRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find VisitEntity with id: [%s]".formatted(visitId)));
    }

    List<VisitEntity> findAllByDate(LocalDate date) {
        return visitJpaRepository.findAll().stream()
                .filter(visit -> visit.getStartTime().toLocalDate().equals(date))
                .toList();
    }



    // do usunięcia, takie metody są generowane z JpaRepo
//    List<VisitEntity> findAllByDoctorId(Integer doctorId) {
//        return visitJpaRepository.findAll().stream()
//                .filter(entity -> entity.getDoctor().getDoctorId().equals(doctorId))
//                .toList();
//    }
//
//    List<VisitEntity> findAllByPatientId(Integer patientId) {
//        return visitJpaRepository.findAll().stream()
//                .filter(entity -> entity.getPatient().getPatientId().equals(patientId))
//                .toList();
//    }
}
