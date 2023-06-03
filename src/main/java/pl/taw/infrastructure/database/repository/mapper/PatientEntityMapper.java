package pl.taw.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.domain.Patient;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.infrastructure.database.entity.PatientEntity;

@Mapper(componentModel = "sprint", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientEntityMapper {

    PatientDTO mapFromEntity(PatientEntity patientEntity);
}
