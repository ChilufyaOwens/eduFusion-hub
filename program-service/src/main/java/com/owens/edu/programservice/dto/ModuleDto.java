package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {
    private String name;
    private String description;
    private String moduleCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isComplete;
    private Integer failedCourseCount;
    private Integer order;
}
