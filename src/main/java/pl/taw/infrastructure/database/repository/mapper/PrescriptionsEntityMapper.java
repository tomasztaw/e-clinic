package pl.taw.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrescriptionsEntityMapper {

    PrescriptionDTO mapFromEntity(PrescriptionEntity prescriptionEntity);

}
