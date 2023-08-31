package com.owens.edu.programservice.dto;

import com.owens.edu.programservice.constants.RequirementType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRequirementResponse {
    private Long programId;
    private String requirementDescription;
    private RequirementType requirementType;
    private Integer minimumCredits;
    private Set<RequiredCourseDto> requiredCourses;
    private LocalDateTime createdAt;
}
