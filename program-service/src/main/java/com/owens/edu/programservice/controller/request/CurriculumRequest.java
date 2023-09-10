package com.owens.edu.programservice.controller.request;

import com.owens.edu.programservice.dto.ModuleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Long programId;
    private Set<ModuleDto> modules;
}
