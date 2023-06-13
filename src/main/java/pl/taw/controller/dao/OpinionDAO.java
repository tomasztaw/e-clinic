package pl.taw.controller.dao;

import pl.taw.controller.dto.OpinionDTO;
import pl.taw.infrastructure.database.entity.OpinionEntity;

import java.util.List;

public interface OpinionDAO {

    OpinionEntity findById(Integer opinionId);

    List<OpinionDTO> findByPatientId(Integer patientId);

    List<OpinionDTO> findByDoctorId(Integer doctorId);

    // dla encji opinion
    List<OpinionEntity> findAllOpinionByDoctorId(Integer doctorId);

    void save(OpinionEntity opinionEntity);

    List<OpinionDTO> findAll();

    OpinionDTO findDTOById(Integer opinionId);

    void delete(OpinionEntity opinionForDelete);

}
