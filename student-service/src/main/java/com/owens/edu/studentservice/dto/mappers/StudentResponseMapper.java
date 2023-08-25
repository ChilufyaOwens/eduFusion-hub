package com.owens.edu.studentservice.dto.mappers;

import com.owens.edu.studentservice.dto.StudentResponse;
import com.owens.edu.studentservice.entity.Address;
import com.owens.edu.studentservice.entity.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentResponseMapper {
    Student toEntity(StudentResponse studentResponse);

    @AfterMapping
    default void linkAddress(@MappingTarget Student student) {
        Address address = student.getAddress();
        if (address != null) {
            address.setStudent(student);
        }
    }

    @AfterMapping
    default void linkEmergencyContacts(@MappingTarget Student student) {
        student.getEmergencyContacts().forEach(emergencyContact -> emergencyContact.setStudent(student));
    }

    StudentResponse toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentResponse studentResponse, @MappingTarget Student student);
}