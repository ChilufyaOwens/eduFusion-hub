package com.owens.edu.programservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    @NotBlank
    private Long studentId;
    @NotBlank
    private Long programId;
    private String enrollmentDate;
}
