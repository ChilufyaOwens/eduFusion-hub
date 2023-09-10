package com.owens.edu.programservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long id;
    @NotNull
    private Long courseId;
    @NotNull
    private String courseName;
    private String courseCode;
    private String description;
    @NotNull
    private String duration;
}
