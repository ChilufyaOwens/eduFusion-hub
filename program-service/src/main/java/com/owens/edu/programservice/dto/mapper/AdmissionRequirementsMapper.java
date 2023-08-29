package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.AdmissionRequirementsDto;
import com.owens.edu.programservice.entity.AdmissionRequirements;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdmissionRequirementsMapper {
    AdmissionRequirements toEntity(AdmissionRequirementsDto admissionRequirementsDto);

    @AfterMapping
    default void linkRequiredCourses(@MappingTarget AdmissionRequirements admissionRequirements) {
        admissionRequirements.getRequiredCourses().forEach(requiredCours -> requiredCours.setAdmissionRequirements(admissionRequirements));
    }

    AdmissionRequirementsDto toDto(AdmissionRequirements admissionRequirements);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdmissionRequirements partialUpdate(AdmissionRequirementsDto admissionRequirementsDto, @MappingTarget AdmissionRequirements admissionRequirements);
}