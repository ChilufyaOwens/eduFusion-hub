package com.owens.edu.programservice.controller.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProgramRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long coordinatorId;
    private String coordinatorName;
    private String departmentId;
    private String departmentName;
    private String degreeType;
}
