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
//        return opinionDAO.findByDoctorId(doctorId).stream()
//                .toList();
    }

    public List<OpinionEntity> getDoctorOpinionsEntity(Integer doctorId) {
        return opinionDAO.findAllOpinionByDoctorId(doctorId);
    }

    public List<OpinionDTO> getPatientOpinions(Integer patientId) {
        return opinionDAO.findByPatientId(patientId);
    }

//    public List<Opinion> getDoctorOpinionsDomain(Integer doctorId) {
//        return opinionDAO.findByDoctorId(doctorId).stream()
//                .map(opinionMapper::map)
//                .toList();
//    }

}
