package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
    private Long studentId;
    private Long programId;
    private LocalDate enrollmentDate;
}
