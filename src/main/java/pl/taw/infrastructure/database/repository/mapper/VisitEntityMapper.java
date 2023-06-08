package pl.taw.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dao.VisitDAO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.infrastructure.database.entity.VisitEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitEntityMapper {

    VisitDTO mapFromEntity(VisitEntity visitEntity);

}
