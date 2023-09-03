package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.ProgramEventResponse;
import com.owens.edu.programservice.entity.ProgramEvent;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramEventResponseMapper {
    ProgramEvent toEntity(ProgramEventResponse programEventResponse);

    ProgramEventResponse toDto(ProgramEvent programEvent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramEvent partialUpdate(ProgramEventResponse programEventResponse, @MappingTarget ProgramEvent programEvent);
}