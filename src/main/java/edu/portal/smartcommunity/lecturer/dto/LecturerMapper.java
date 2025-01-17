package edu.portal.smartcommunity.lecturer.dto;

import edu.portal.smartcommunity.lecturer.model.Lecturer;
import edu.portal.smartcommunity.mapper.JsonNullableMapper;
import edu.portal.smartcommunity.mapper.ReferenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class LecturerMapper {

    public abstract Lecturer map(LecturerCreateDTO dto);

    public abstract LecturerDTO map(Lecturer model);

    public abstract LecturerCreateDTO mapToCreateDTO(Lecturer model);

    public abstract void update(LecturerUpdateDTO updateDTO, @MappingTarget Lecturer model);
}
