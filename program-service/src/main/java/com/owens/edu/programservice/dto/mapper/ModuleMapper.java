package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.controller.request.ModuleRequest;
import com.owens.edu.programservice.entity.Module;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModuleMapper {
    Module toEntity(ModuleRequest moduleRequest);

    ModuleRequest toDto(Module module);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Module partialUpdate(ModuleRequest moduleRequest, @MappingTarget Module module);
}