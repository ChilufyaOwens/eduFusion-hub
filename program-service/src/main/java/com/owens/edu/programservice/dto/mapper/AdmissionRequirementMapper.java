package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.controller.request.AdmissionRequirementRequest;
import com.owens.edu.programservice.entity.AdmissionRequirement;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdmissionRequirementMapper {
    AdmissionRequirement toEntity(AdmissionRequirementRequest admissionRequirementRequest);

    @AfterMapping
    default void linkRequiredCourses(@MappingTarget AdmissionRequirement admissionRequirement) {
        admissionRequirement.getRequiredCourses().forEach(requiredCours -> requiredCours.setAdmissionRequirement(admissionRequirement));
    }

    AdmissionRequirementRequest toDto(AdmissionRequirement admissionRequirement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdmissionRequirement partialUpdate(AdmissionRequirementRequest admissionRequirementRequest, @MappingTarget AdmissionRequirement admissionRequirement);
}