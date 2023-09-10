package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.CurriculumResponse;
import com.owens.edu.programservice.entity.Curriculum;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurriculumResponseMapper {
    Curriculum toEntity(CurriculumResponse curriculumResponse);

    @AfterMapping
    default void linkModules(@MappingTarget Curriculum curriculum) {
        curriculum.getModules().forEach(module -> module.setCurriculum(curriculum));
    }

    CurriculumResponse toDto(Curriculum curriculum);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Curriculum partialUpdate(CurriculumResponse curriculumResponse, @MappingTarget Curriculum curriculum);
}