package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.CurriculumDto;
import com.owens.edu.programservice.entity.Curriculum;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurriculumMapper {
    Curriculum toEntity(CurriculumDto curriculumDto);

    @AfterMapping
    default void linkCurriculumItems(@MappingTarget Curriculum curriculum) {
        curriculum.getCurriculumItems().forEach(curriculumItem -> curriculumItem.setCurriculum(curriculum));
    }

    CurriculumDto toDto(Curriculum curriculum);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Curriculum partialUpdate(CurriculumDto curriculumDto, @MappingTarget Curriculum curriculum);
}