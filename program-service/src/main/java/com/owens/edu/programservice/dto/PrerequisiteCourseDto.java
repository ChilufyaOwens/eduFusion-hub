package com.owens.edu.programservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrerequisiteCourseDto {
    private String name;
    private String description;
    private  Integer minimumCredits;
}
