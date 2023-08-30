package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.ProgramResponse;
import com.owens.edu.programservice.entity.Program;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramResponseMapper {
    Program toEntity(ProgramResponse programResponse);

    ProgramResponse toDto(Program program);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Program partialUpdate(ProgramResponse programResponse, @MappingTarget Program program);
}