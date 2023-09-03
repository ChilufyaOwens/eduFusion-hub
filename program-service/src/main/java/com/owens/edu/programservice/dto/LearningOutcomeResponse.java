package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningOutcomeResponse {
    private Long id;
    private Long programId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
