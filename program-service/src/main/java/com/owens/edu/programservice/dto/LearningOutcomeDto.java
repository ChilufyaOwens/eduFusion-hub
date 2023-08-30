package com.owens.edu.programservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningOutcomeDto {
    private Long programId;
    private String name;
    private String description;
}
