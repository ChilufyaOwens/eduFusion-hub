package com.owens.edu.programservice.controller.request;

import com.owens.edu.programservice.constants.DegreeType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate startDate;
    @NotBlank
    private LocalDate endDate;
    private Long coordinatorId;
    private String coordinatorName;
    private String departmentId;
    private String departmentName;
    @NotBlank
    private String degreeType;
}
