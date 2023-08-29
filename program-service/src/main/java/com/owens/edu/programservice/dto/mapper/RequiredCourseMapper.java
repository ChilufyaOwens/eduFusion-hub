package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.RequiredCourseDto;
import com.owens.edu.programservice.entity.RequiredCourse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequiredCourseMapper {
    RequiredCourse toEntity(RequiredCourseDto requiredCourseDto);

    RequiredCourseDto toDto(RequiredCourse requiredCourse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RequiredCourse partialUpdate(RequiredCourseDto requiredCourseDto, @MappingTarget RequiredCourse requiredCourse);
}