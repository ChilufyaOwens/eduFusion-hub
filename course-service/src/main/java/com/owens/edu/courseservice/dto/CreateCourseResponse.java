package com.owens.edu.courseservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;
    private Integer enrolledStudents;
    private Long programId;
    private String programName;
}
