package com.owens.edu.studentservice.controller.api;

import com.owens.edu.studentservice.controller.request.StudentRequest;
import com.owens.edu.studentservice.controller.request.StudentUpdateRequest;
import com.owens.edu.studentservice.dto.ApiResponse;
import com.owens.edu.studentservice.dto.StudentResponse;
import com.owens.edu.studentservice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping(name = "CreateStudent")
    public ResponseEntity<ApiResponse> registerStudent(@Valid @RequestBody StudentRequest request){
        StudentResponse savedStudent = studentService.registerStudent(request);

        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED)
                .message(String.format("Student successfully created with student number: %s",
                        savedStudent.getStudentNumber()))
                .data(savedStudent)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", name = "GetStudentById")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable(name = "id") Long studentId){
        if(studentId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(false)
                    .status(HttpStatus.BAD_REQUEST)
                    .message("StudentId cannot be null or empty")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        StudentResponse response = studentService.getStudentById(studentId);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .status(HttpStatus.OK)
                .message(String.format("Student with studentId: %s", studentId))
                .data(response)
                .build(), HttpStatus.OK);

    }

    @GetMapping(value = "/number/{studentNumber}", name = "GetStudentByNumber")
    public ResponseEntity<ApiResponse> getStudentByNumber(@PathVariable(name = "studentNumber") String studentNumber){
        if (StringUtils.isBlank(studentNumber)) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(false)
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Student number cannot be null or empty")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        StudentResponse studentByNumber = studentService.getStudentByNumber(studentNumber);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .status(HttpStatus.OK)
                .message(String.format("Student with student number: %s", studentNumber))
                .data(studentByNumber)
                .build(), HttpStatus.OK);
    }

    @GetMapping(name = "GetAllStudents")
    public ResponseEntity<ApiResponse> getAllStudents(){
        List<StudentResponse> allStudents = studentService.getAllStudents();
        if(allStudents.isEmpty()){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(true)
                    .status(HttpStatus.OK)
                    .message("No student registered")
                    .data(null)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .status(HttpStatus.OK)
                .message("All Registered students")
                .data(allStudents)
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", name = "UpdateStudentDetails")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable(name = "id") Long studentId,
                                                     @Valid @RequestBody StudentUpdateRequest request){
        if(studentId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(true)
                    .status(HttpStatus.BAD_GATEWAY)
                    .message("StudentId cannot be null")
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        StudentResponse response = studentService.updateStudentById(studentId, request);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .status(HttpStatus.OK)
                .message(String.format("Student details with student number: %s updated.", response.getStudentNumber()))
                .data(response)
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> deleteStudentId(@PathVariable(name = "id") Long studentId){
        if(studentId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(true)
                    .status(HttpStatus.BAD_GATEWAY)
                    .message("StudentId cannot be null")
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        String deleteStudentById = studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .status(HttpStatus.OK)
                .message(String.format("Student with studentId %s deleted.", studentId))
                .data(deleteStudentById)
                .build(), HttpStatus.OK);
    }
}
