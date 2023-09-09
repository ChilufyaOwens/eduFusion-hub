package com.owens.edu.programservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProgramRequest {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String startDate;
    @NotBlank
    private String endDate;
    private Long coordinatorId;
    private String coordinatorName;
    private String departmentId;
    private String departmentName;
    @NotBlank
    private String degreeType;
}
