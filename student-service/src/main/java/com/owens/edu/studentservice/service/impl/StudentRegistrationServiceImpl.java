package com.owens.edu.studentservice.service.impl;

import com.owens.edu.studentservice.entity.Address;
import com.owens.edu.studentservice.entity.EmergencyContact;
import com.owens.edu.studentservice.entity.Student;
import com.owens.edu.studentservice.respository.AddressRepository;
import com.owens.edu.studentservice.respository.EmergencyContactRepository;
import com.owens.edu.studentservice.respository.StudentRepository;
import com.owens.edu.studentservice.service.StudentRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "STUDENT_REGISTRATION_SERVICE")
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;
    private final EmergencyContactRepository emergencyContactRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Student registerStudent(Student studentRequest) {
        Student student = createStudentFromRequest(studentRequest);
        Student registeredStudent = studentRepository.save(student);

        Address address = saveAddress(registeredStudent, studentRequest.getAddress());
        registeredStudent.setAddress(address);

        if (!studentRequest.getEmergencyContacts().isEmpty()) {
            Set<EmergencyContact> contacts = saveEmergencyContacts(registeredStudent, studentRequest.getEmergencyContacts());
            registeredStudent.setEmergencyContacts(contacts);
        }

        return registeredStudent;
    }

    private Student createStudentFromRequest(Student studentRequest) {
        return new Student(
                studentRequest.getId(),
                studentRequest.getStudentNumber(),
                studentRequest.getFirstname(),
                studentRequest.getOtherName(),
                studentRequest.getLastname(),
                studentRequest.getDob(),
                studentRequest.getGender(),
                studentRequest.getIdentificationNumber(),
                studentRequest.getNationality(),
                studentRequest.getContactNumber(),
                studentRequest.getEmail(),
                studentRequest.getStatus());
    }

    private Address saveAddress(Student registeredStudent, Address address) {
        address.setStudent(registeredStudent);
        return addressRepository.save(address);
    }

    private Set<EmergencyContact> saveEmergencyContacts(Student registeredStudent, Set<EmergencyContact> emergencyContacts) {
        return emergencyContacts.stream()
                .map(contact -> {
                    contact.setStudent(registeredStudent);
                    return emergencyContactRepository.save(contact);
                })
                .collect(Collectors.toSet());
    }
}

