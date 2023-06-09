package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.controller.dto.VisitsDTO;
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
    public List<VisitDTO> findByDoctorId(Integer doctorId) {
        return visitJpaRepository.findAll().stream()
                .filter(visit -> visit.getDoctorId().equals(doctorId))
                .map(visitEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<VisitDTO> findByPatientId(Integer patientId) {
        return visitJpaRepository.findAll().stream()
                .filter(visit -> visit.getPatientId().equals(patientId))
                .map(visitEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<VisitDTO> findAll() {
        return visitJpaRepository.findAll().stream()
                .map(visitEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public VisitsDTO findByDate(LocalDate date) {
        return VisitsDTO.of(visitJpaRepository.findAll().stream()
                .filter(visit -> visit.getStartTime().toLocalDate().equals(date))
                .map(visitEntityMapper::mapFromEntity)
                .toList());
    }

    @Override
    public void saveVisit(VisitEntity newVisit) {
        visitJpaRepository.save(newVisit);
    }

    @Override
    public void delete(VisitEntity visitForDelete) {
        visitJpaRepository.delete(visitForDelete);
    }

    @Override
    public VisitEntity findById(Integer visitId) {
        return visitJpaRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find VisitEntity with id: [%s]".formatted(visitId)));
    }

    @Override
    public VisitDTO findDTOById(Integer visitId) {
        return visitJpaRepository.findById(visitId)
                .map(visitEntityMapper::mapFromEntity)
                .orElseThrow(() -> new NotFoundException("Could not found VisitEntity with id: [%s]".formatted(visitId)));
    }

    List<VisitEntity> findAllByDate(LocalDate date) {
        return visitJpaRepository.findAll().stream()
                .filter(visit -> visit.getStartTime().toLocalDate().equals(date))
                .toList();
    }


}
