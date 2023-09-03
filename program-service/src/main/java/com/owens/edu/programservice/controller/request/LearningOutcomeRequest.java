package com.owens.edu.programservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningOutcomeRequest {
    @NotNull
    private Long programId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
