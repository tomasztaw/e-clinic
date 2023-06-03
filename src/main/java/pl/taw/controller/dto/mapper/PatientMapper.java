package pl.taw.controller.dto.mapper;

/**
 * Automatyczne mapowanie obiektów PatientEntity na PatientDTO (MapStruct)
 * componentModel = "spring" -> uwzględnia mechanizmy zarządzanie zależnościami w Springu
 * unmappedTargetPolicy = ReportingPolicy.IGNORE -> nierozwiązane pola w obiekcie docelowym są ignorowane
 */

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.infrastructure.database.entity.PatientEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    PatientDTO map(PatientEntity patientEntity);

}
