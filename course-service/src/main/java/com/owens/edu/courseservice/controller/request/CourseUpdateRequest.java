package com.owens.edu.courseservice.controller.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateRequest {
    private String description;
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;
    private Integer enrolledStudents;
    private Long programId;
    private String programName;
}

