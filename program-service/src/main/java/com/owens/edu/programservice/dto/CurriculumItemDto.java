package com.owens.edu.programservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumItemDto {
    private String name;
    private String description;
    private String moduleCode;
    private Integer order; // The order in which the item appears in the curriculum
    private Long courseId;
    private String courseName;
}
