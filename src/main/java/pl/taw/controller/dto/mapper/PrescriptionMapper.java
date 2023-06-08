package pl.taw.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrescriptionMapper {

    PrescriptionDTO map(PrescriptionEntity prescriptionEntity);

}
