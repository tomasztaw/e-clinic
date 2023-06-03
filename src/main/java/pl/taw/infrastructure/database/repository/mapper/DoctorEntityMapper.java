package pl.taw.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.domain.Doctor;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.infrastructure.database.entity.DoctorEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorEntityMapper {

    //@Mapping(target = "serviceDoctors", ignore = true) -> dla ignorowania danego pola podczas mapaowania
    DoctorDTO mapFromEntity(DoctorEntity doctorEntity);

}
