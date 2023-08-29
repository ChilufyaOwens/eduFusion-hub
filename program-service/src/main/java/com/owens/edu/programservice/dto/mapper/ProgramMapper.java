package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.entity.Program;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramMapper {
    Program toEntity(ProgramDto programDto);

    ProgramDto toDto(Program program);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Program partialUpdate(ProgramDto programDto, @MappingTarget Program program);
}