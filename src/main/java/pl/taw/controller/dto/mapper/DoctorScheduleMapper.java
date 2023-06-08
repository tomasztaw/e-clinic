package pl.taw.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.taw.controller.dto.DoctorScheduleDTO;
import pl.taw.infrastructure.database.entity.DoctorScheduleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorScheduleMapper {

    DoctorScheduleDTO map(DoctorScheduleEntity doctorScheduleEntity);
}
