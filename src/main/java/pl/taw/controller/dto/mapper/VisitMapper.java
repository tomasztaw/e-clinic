package pl.taw.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.infrastructure.database.entity.VisitEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VisitMapper {

    VisitDTO map(VisitEntity visitEntity);

}
