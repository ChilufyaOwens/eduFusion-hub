package com.owens.edu.studentservice.dto.mappers;

import com.owens.edu.studentservice.controller.request.StudentRequest;
import com.owens.edu.studentservice.entity.Address;
import com.owens.edu.studentservice.entity.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    Student toEntity(StudentRequest studentRequest);

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

    StudentRequest toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentRequest studentRequest, @MappingTarget Student student);
}