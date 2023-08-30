package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.ProgramEventDto;
import com.owens.edu.programservice.entity.ProgramEvent;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramEventMapper {
    ProgramEvent toEntity(ProgramEventDto programEventDto);

    ProgramEventDto toDto(ProgramEvent programEvent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramEvent partialUpdate(ProgramEventDto programEventDto, @MappingTarget ProgramEvent programEvent);
}