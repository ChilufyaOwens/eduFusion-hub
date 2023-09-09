package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private String description;
    private String duration;
    private ModuleDto module;
    private LocalDateTime createdAt;

}
