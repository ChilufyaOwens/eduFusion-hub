package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.ModuleResponse;
import com.owens.edu.programservice.entity.Module;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModuleResponseMapper {
    Module toEntity(ModuleResponse moduleResponse);

    ModuleResponse toDto(Module module);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Module partialUpdate(ModuleResponse moduleResponse, @MappingTarget Module module);
}