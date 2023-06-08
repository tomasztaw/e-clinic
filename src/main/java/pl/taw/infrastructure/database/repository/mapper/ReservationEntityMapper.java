package pl.taw.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.ReservationDTO;
import pl.taw.infrastructure.database.entity.ReservationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationEntityMapper {

    ReservationDTO mapFromEntity(ReservationEntity reservationEntity);

}
