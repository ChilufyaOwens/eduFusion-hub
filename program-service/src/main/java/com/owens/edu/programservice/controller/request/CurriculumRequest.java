package com.owens.edu.programservice.controller.request;

import com.owens.edu.programservice.dto.CurriculumItemDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumRequest {
    @NotNull
    private Long programId;
    @NotBlank
    private String description;
    @NotNull
    @NotEmpty
    private Set<CurriculumItemDto> curriculumItems;
}
