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
    private ProgramResponse program;
    private String name;
    private String description;
    private Set<ModuleDto> modules;
    private LocalDateTime createdAt;
}
