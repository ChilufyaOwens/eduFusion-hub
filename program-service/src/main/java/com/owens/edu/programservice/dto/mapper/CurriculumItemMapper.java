package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.CurriculumItemDto;
import com.owens.edu.programservice.entity.CurriculumItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurriculumItemMapper {
    CurriculumItem toEntity(CurriculumItemDto curriculumItemDto);

    CurriculumItemDto toDto(CurriculumItem curriculumItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CurriculumItem partialUpdate(CurriculumItemDto curriculumItemDto, @MappingTarget CurriculumItem curriculumItem);
}