package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.controller.request.CurriculumRequest;
import com.owens.edu.programservice.entity.Curriculum;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurriculumMapper {
    Curriculum toEntity(CurriculumRequest curriculumRequest);

    @AfterMapping
    default void linkModules(@MappingTarget Curriculum curriculum) {
        curriculum.getModules().forEach(module -> module.setCurriculum(curriculum));
    }

    CurriculumRequest toDto(Curriculum curriculum);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Curriculum partialUpdate(CurriculumRequest curriculumRequest, @MappingTarget Curriculum curriculum);
}