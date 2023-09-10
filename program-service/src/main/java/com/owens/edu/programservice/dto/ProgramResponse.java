package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long coordinatorId;
    private String coordinatorName;
    private String departmentId;
    private String departmentName;
    private String degreeType;
    private LocalDateTime createdAt;
}
