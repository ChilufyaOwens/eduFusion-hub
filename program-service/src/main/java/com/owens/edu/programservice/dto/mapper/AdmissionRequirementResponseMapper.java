package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.AdmissionRequirementResponse;
import com.owens.edu.programservice.entity.AdmissionRequirement;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdmissionRequirementResponseMapper {
    AdmissionRequirement toEntity(AdmissionRequirementResponse admissionRequirementResponse);

    @AfterMapping
    default void linkRequiredCourses(@MappingTarget AdmissionRequirement admissionRequirement) {
        admissionRequirement.getRequiredCourses().forEach(requiredCours -> requiredCours.setAdmissionRequirement(admissionRequirement));
    }

    AdmissionRequirementResponse toDto(AdmissionRequirement admissionRequirement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdmissionRequirement partialUpdate(AdmissionRequirementResponse admissionRequirementResponse, @MappingTarget AdmissionRequirement admissionRequirement);
}