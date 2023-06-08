package pl.taw.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.infrastructure.database.entity.OpinionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OpinionMapper {

    OpinionDTO map(OpinionEntity opinionEntity);
}
