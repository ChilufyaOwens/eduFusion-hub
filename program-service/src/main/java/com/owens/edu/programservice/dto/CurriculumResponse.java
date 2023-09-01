package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumResponse {
    private ProgramResponse programResponse;
    private String description;
    private Set<CurriculumItemDto> curriculumItems;
    private LocalDateTime createdAt;
}
