package com.owens.edu.programservice.dto;

import com.owens.edu.programservice.constants.RequirementType;
import com.owens.edu.programservice.entity.RequiredCourse;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRequirementsDto {
    private Long programId;
    private String requirementDescription;
    private RequirementType requirementType;
    private Integer minimumCredits;
    private Set<RequiredCourse> requiredCourses;
}
