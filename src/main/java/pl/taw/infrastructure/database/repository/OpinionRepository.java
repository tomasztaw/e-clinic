package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.OpinionDAO;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.OpinionEntity;
import pl.taw.infrastructure.database.repository.jpa.OpinionJpaRepository;
import pl.taw.infrastructure.database.repository.mapper.OpinionEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class OpinionRepository implements OpinionDAO {

    private final OpinionJpaRepository opinionJpaRepository;
    private final OpinionEntityMapper opinionEntityMapper;

    @Override
    public OpinionEntity findById(Integer opinionId) {
        return opinionJpaRepository.findById(opinionId)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find OpinionEntity with id: [%s]".formatted(opinionId)));
    }

    @Override
    public List<OpinionDTO> findByPatientId(Integer patientId) {
        return opinionJpaRepository.findAll().stream()
//                .filter(opinion -> opinion.getPatient().getPatientId().equals(patientId))
                .filter(opinion -> opinion.getPatientId().equals(patientId))
                .map(opinionEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<OpinionDTO> findByDoctorId(Integer doctorId) {
        return opinionJpaRepository.findAll().stream()
//                .filter(opinion -> opinion.getDoctor().getDoctorId().equals(doctorId))
                .filter(opinion -> opinion.getDoctorId().equals(doctorId))
                .map(opinionEntityMapper::mapFromEntity)
                .toList();
    }

    // próba zwrócenia encji z opiniami
    @Override
    public List<OpinionEntity> findAllOpinionByDoctorId(Integer doctorId) {
        return opinionJpaRepository.findAll().stream()
//                .filter(opinion -> opinion.getDoctor().getDoctorId().equals(doctorId))
                .filter(opinion -> opinion.getDoctorId().equals(doctorId))
                .toList();
    }


    @Override
    public void save(OpinionEntity existingOpinion) {
        opinionJpaRepository.save(existingOpinion);
    }
}
