package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.OpinionDAO;
import pl.taw.controller.domain.Opinion;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.mapper.OpinionMapper;
import pl.taw.infrastructure.database.entity.OpinionEntity;
import pl.taw.infrastructure.database.repository.mapper.OpinionEntityMapper;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OpinionService {

    private final OpinionDAO opinionDAO;
//    private final OpinionMapper opinionMapper;
//    private final OpinionEntityMapper opinionEntityMapper;

    // czyli serwis pobiera poprzez warstwę DAO obiekt Encji zamieniony na DTO, który jest pobierany poprzez
    // Repozytorium na bazie JPA i następnie przekazywany do kontrolera
    public List<OpinionDTO> getDoctorOpinions(Integer doctorId) {
        return opinionDAO.findByDoctorId(doctorId);
    }

    public List<OpinionEntity> getDoctorOpinionsEntity(Integer doctorId) {
        return opinionDAO.findAllOpinionByDoctorId(doctorId);
    }

    public List<OpinionDTO> getPatientOpinions(Integer patientId) {
        return opinionDAO.findByPatientId(patientId);
    }


//    public void updateOpinion(OpinionDTO opinionDTO) {
//
//    }

    public void updateOpinion(OpinionDTO opinionDTO) {
        OpinionEntity existingOpinion = opinionDAO.findById(opinionDTO.getOpinionId());

        if (existingOpinion != null) {
            existingOpinion.setComment(opinionDTO.getComment());
            opinionDAO.save(existingOpinion);
        } else {
            throw new IllegalArgumentException("Opinion with ID " + opinionDTO.getOpinionId() + " does not exist.");
        }
    }

//    public void addOpinion(OpinionDTO opinionDTO) {
//
//    }

    public void addOpinion(OpinionDTO opinionDTO) {
        OpinionEntity newOpinion = new OpinionEntity();
        newOpinion.setDoctor(opinionDTO.getDoctor());
        newOpinion.setPatient(opinionDTO.getPatient());
        newOpinion.setComment(opinionDTO.getComment());
        newOpinion.setCreatedAt(LocalDateTime.now());

        opinionDAO.save(newOpinion);
    }

}
