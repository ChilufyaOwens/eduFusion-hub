package com.owens.edu.courseservice.service.impl;

import com.owens.edu.courseservice.controller.request.CourseUpdateRequest;
import com.owens.edu.courseservice.controller.request.CreateCourseRequest;
import com.owens.edu.courseservice.dto.CreateCourseResponse;
import com.owens.edu.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "COURSE_SERVICE")
public class CourseServiceImpl implements CourseService {
    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request) {
        return null;
    }

    @Override
    public CreateCourseResponse getCourseById(Long courseId) {
        return null;
    }

    @Override
    public List<CreateCourseResponse> getCourses() {
        return new ArrayList<>();
    }

    @Override
    public List<CreateCourseResponse> getCoursesByProgramId(Long programId) {
        return new ArrayList<>();
    }

    @Override
    public CreateCourseResponse updateCourse(Long courseId, CourseUpdateRequest request) {
        return null;
    }

    @Override
    public String deleteCourseById(Long courseId) {
        return null;
    }
}
