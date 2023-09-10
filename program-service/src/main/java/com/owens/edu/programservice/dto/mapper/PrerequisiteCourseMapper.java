package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.PrerequisiteCourseDto;
import com.owens.edu.programservice.entity.PrerequisiteCourse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrerequisiteCourseMapper {
    PrerequisiteCourse toEntity(PrerequisiteCourseDto prerequisiteCourseDto);

    PrerequisiteCourseDto toDto(PrerequisiteCourse prerequisiteCourse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PrerequisiteCourse partialUpdate(PrerequisiteCourseDto prerequisiteCourseDto, @MappingTarget PrerequisiteCourse prerequisiteCourse);
}