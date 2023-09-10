package com.owens.edu.programservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest {
    @NotBlank
    private String name;
    private String description;
    private String moduleCode;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    private Boolean isComplete;
    private Integer failedCourseCount;
    @NotNull
    private Integer order;
    @NotNull
    private Long curriculumId;
}
