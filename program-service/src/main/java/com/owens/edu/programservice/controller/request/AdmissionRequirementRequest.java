package com.owens.edu.programservice.controller.request;

import com.owens.edu.programservice.dto.PrerequisiteCourseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRequirementRequest {
    @NotNull
    private Long programId;
    private String requirementDescription;
    @NotBlank
    @NotNull
    private String requirementType;
    @NotNull
    private Integer minimumCredits;
    @NotEmpty
    private Set<PrerequisiteCourseDto> prerequisiteCourses;


}
