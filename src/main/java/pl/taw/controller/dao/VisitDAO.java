package pl.taw.controller.dao;

import pl.taw.controller.dto.VisitDTO;
import pl.taw.infrastructure.database.entity.VisitEntity;

import java.util.List;

public interface VisitDAO {

    VisitEntity findById(Integer visit_id);

    List<VisitDTO> findByStatus(String status);
}
