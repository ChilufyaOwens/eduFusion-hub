package com.owens.edu.programservice.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramEventRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String eventDate;
    @NotBlank
    private String eventEndDate;
    @NotNull
    private Long programId;
}
