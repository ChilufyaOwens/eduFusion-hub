package com.owens.edu.courseservice.controller;

import com.owens.edu.courseservice.controller.request.CreateCourseRequest;
import com.owens.edu.courseservice.dto.CreateCourseResponse;
import com.owens.edu.courseservice.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CreateCourseRequest request){
        CreateCourseResponse course = courseService.createCourse(request);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }
}
