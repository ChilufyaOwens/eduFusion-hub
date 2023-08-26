package com.owens.edu.studentservice.service.impl;

import com.owens.edu.commons.constants.Status;
import com.owens.edu.commons.exception.ResourceNotFoundException;
import com.owens.edu.studentservice.controller.request.StudentRequest;
import com.owens.edu.studentservice.controller.request.StudentUpdateRequest;
import com.owens.edu.studentservice.dto.EmergencyContactDto;
import com.owens.edu.studentservice.dto.StudentResponse;
import com.owens.edu.studentservice.dto.mappers.AddressMapper;
import com.owens.edu.studentservice.dto.mappers.EmergencyContactMapper;
import com.owens.edu.studentservice.dto.mappers.StudentMapper;
import com.owens.edu.studentservice.dto.mappers.StudentResponseMapper;
import com.owens.edu.studentservice.entity.EmergencyContact;
import com.owens.edu.studentservice.entity.Student;
import com.owens.edu.studentservice.exception.EmailAlreadyExistException;
import com.owens.edu.studentservice.respository.StudentRepository;
import com.owens.edu.studentservice.service.StudentNumberGenerator;
import com.owens.edu.studentservice.service.StudentRegistrationService;
import com.owens.edu.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "STUDENT_SERVICE")
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentResponseMapper studentResponseMapper;
    private final AddressMapper addressMapper;
    private final EmergencyContactMapper emergencyContactMapper;
    private final StudentNumberGenerator studentNumberGenerator;
    private final StudentRepository studentRepository;
    private final StudentRegistrationService studentRegistrationService;

    /**
     * registerStudent method checks if the student has unique email before registering or saving the new student.
     * New student number is generated and assigned to student.
     *
     * @param request student registration number
     * @return registered student.
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudentResponse registerStudent(StudentRequest request) {
        log.info("Creating student with identification card number: {} and name {}",
                request.getIdentificationNumber(),
                request.getFirstname() + " " + request.getLastname());

        //Check if student with email already exist.
        studentRepository.findStudentByEmail(request.getEmail())
                .ifPresent(student -> {
                    throw new EmailAlreadyExistException(
                            String.format("Student with email: %s already exist.", request.getEmail()));
                });

        //Map create student request to an entity
        Student student = studentMapper.toEntity(request);

        //Get student number
        String studentNumber = studentNumberGenerator.generateStudentNumber();
        student.setStudentNumber(studentNumber);
        student.setStatus(Status.INCOMPLETE_REG);

        Student registeredStudent = studentRegistrationService.registerStudent(student);
        return studentResponseMapper.toDto(registeredStudent);
    }

    /**
     * getStudentById method gets student with the provided studentNumber, if student with the provided studentNumber
     * is not present, resource not found exception will be thrown.
     *
     * @param studentNumber studentNumber of the student we want to get
     * @return student details
     */
    @Override
    public StudentResponse getStudentByNumber(String studentNumber) {
        log.info("Fetching student with student number: {}", studentNumber);
        Student student = studentRepository.findStudentByStudentNumber(studentNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Student", "studentNumber", studentNumber));
        log.info("Fetched student with studentNumber: {}", studentNumber);
        return studentResponseMapper.toDto(student);
    }

    /**
     * getAllStudents method gets all students registered or pending payment
     *
     * @return list of registered students
     */
    @Override
    public List<StudentResponse> getAllStudents() {
        log.info("Fetching all registered students");

        List<StudentResponse> studentResponses = new ArrayList<>();
        studentRepository.findAll()
                .forEach(student -> studentResponses.add(studentResponseMapper.toDto(student)));

        log.info("Fetched all registered students");
        return studentResponses;
    }

    /**
     * We use the map() method on the Optional<Student> returned by findById() to apply the updates to the student
     * if it exists. Inside the map() function, we perform the necessary updates to the student's properties.
     * We use method chaining to call studentRepository.save() to save the updated student object.
     * If the student is not found, the orElseThrow() method is used to throw the exception
     * @param studentId id of the student to update
     * @param request update request dto
     * @return student update response
     */
    @Override
    public StudentResponse updateStudentById(Long studentId, StudentUpdateRequest request) {

        log.info("Updating details of the student with studentId: {}", studentId);
        Student updatedStudent = studentRepository.findById(studentId)
                .map(student -> {
                    student.setContactNumber(request.getContactNumber());
                    student.setAddress(addressMapper.toEntity(request.getAddress()));
                    student.setEmergencyContacts(mapEmergencyContacts(request.getEmergencyContacts()));
                    return studentRepository.save(student);
                }).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId.toString()));

        log.info("Student with studentId: {} updated", studentId);
        return studentResponseMapper.toDto(updatedStudent);
    }

    /**
     * mapEmergencyContacts method use the stream() method to convert the emergencyContacts set into a stream of elements.
     * The map() operation transforms each EmergencyContactDto into an EmergencyContact using the
     * emergencyContactMapper::toEntity method reference.
     * Finally, the collect(Collectors.toSet()) operation collects the stream elements into a Set<EmergencyContact>.
     *
     * @param emergencyContacts set of emergency contacts for the student
     * @return set of mapped emergency contacts
     */
    private Set<EmergencyContact> mapEmergencyContacts(Set<EmergencyContactDto> emergencyContacts) {
        return emergencyContacts.stream()
                .map(emergencyContactMapper::toEntity)
                .collect(Collectors.toSet());
    }

    /**
     * deleteStudentById method uses the ifPresentOrElse() method to handle both cases where the student is found
     * and where it is not found. The studentRepository::delete method reference is used to delete the student if it exists.
     * If the student is not found, a ResourceNotFoundException is thrown using a lambda expression.
     * The String.format() method is used to create the deletion response message.
     * @param studentId used to get a student to delete
     * @return deletion response string
     */
    @Override
    public String deleteStudentById(Long studentId) {
        log.info("Deleting student with studentId: {}", studentId);

        studentRepository.findById(studentId)
                .ifPresentOrElse(
                        studentRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Student", "id", studentId.toString());
                        }
                );

        return String.format("Student with student id %s deleted.", studentId);
    }


    @Override
    public StudentResponse getStudentById(Long studentId) {
        log.info("Getting student with provided studentId");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(
                "Student", "id", studentId.toString()));
        log.info("Fetched student with studentId: {}", studentId);
        return studentResponseMapper.toDto(student);
    }


}
