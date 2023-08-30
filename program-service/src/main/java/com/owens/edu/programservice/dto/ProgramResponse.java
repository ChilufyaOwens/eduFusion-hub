package com.owens.edu.programservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
