package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.CourseResponse;
import com.owens.edu.programservice.entity.Course;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseResponseMapper {
    Course toEntity(CourseResponse courseResponse);

    CourseResponse toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(CourseResponse courseResponse, @MappingTarget Course course);
}