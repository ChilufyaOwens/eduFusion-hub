package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.controller.request.ProgramEventRequest;
import com.owens.edu.programservice.entity.ProgramEvent;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramEventMapper {
    ProgramEvent toEntity(ProgramEventRequest programEventRequest);

    ProgramEventRequest toDto(ProgramEvent programEvent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProgramEvent partialUpdate(ProgramEventRequest programEventRequest, @MappingTarget ProgramEvent programEvent);
}