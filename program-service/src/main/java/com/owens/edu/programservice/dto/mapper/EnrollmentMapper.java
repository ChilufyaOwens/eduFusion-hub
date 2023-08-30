package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.EnrollmentDto;
import com.owens.edu.programservice.entity.Enrollment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnrollmentMapper {
    Enrollment toEntity(EnrollmentDto enrollmentDto);

    EnrollmentDto toDto(Enrollment enrollment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enrollment partialUpdate(EnrollmentDto enrollmentDto, @MappingTarget Enrollment enrollment);
}