package pl.taw.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.ReservationDTO;
import pl.taw.infrastructure.database.entity.ReservationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    ReservationDTO map(ReservationEntity reservationEntity);

}
