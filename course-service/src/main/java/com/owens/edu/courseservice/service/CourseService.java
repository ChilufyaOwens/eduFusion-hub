package com.owens.edu.courseservice.service;

import com.owens.edu.courseservice.controller.request.CourseUpdateRequest;
import com.owens.edu.courseservice.controller.request.CreateCourseRequest;
import com.owens.edu.courseservice.dto.CreateCourseResponse;

import java.util.List;

public interface CourseService {
    CreateCourseResponse createCourse(CreateCourseRequest request);
    CreateCourseResponse getCourseById(Long courseId);

    List<CreateCourseResponse> getCourses();
    List<CreateCourseResponse> getCoursesByProgramId(Long programId);
    CreateCourseResponse updateCourse(Long courseId, CourseUpdateRequest request);

    String deleteCourseById(Long courseId);
}
