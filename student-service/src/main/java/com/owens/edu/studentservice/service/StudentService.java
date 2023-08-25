package com.owens.edu.studentservice.service;

import com.owens.edu.studentservice.controller.request.StudentRequest;
import com.owens.edu.studentservice.controller.request.StudentUpdateRequest;
import com.owens.edu.studentservice.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse registerStudent(StudentRequest request);
    StudentResponse getStudentByNumber(String studentNumber);
    List<StudentResponse> getAllStudents();
    StudentResponse updateStudentById(Long studentId, StudentUpdateRequest studentRequest);
    String deleteStudentById(Long studentId);
    StudentResponse getStudentById(Long studentId);
}
