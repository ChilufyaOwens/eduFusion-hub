package com.owens.edu.studentservice.dto.mappers;

import com.owens.edu.studentservice.dto.EmergencyContactDto;
import com.owens.edu.studentservice.entity.EmergencyContact;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmergencyContactMapper {
    EmergencyContact toEntity(EmergencyContactDto emergencyContactDto);

    EmergencyContactDto toDto(EmergencyContact emergencyContact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmergencyContact partialUpdate(EmergencyContactDto emergencyContactDto, @MappingTarget EmergencyContact emergencyContact);
}