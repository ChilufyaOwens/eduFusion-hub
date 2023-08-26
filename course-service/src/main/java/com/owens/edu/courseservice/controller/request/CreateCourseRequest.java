package com.owens.edu.courseservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    private String description;
    @NotBlank
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;
    private Integer enrolledStudents;
    @NotNull
    private Long programId;
    @NotBlank
    private String programName;
}
