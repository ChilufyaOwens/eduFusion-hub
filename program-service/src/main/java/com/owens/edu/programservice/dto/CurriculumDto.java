package com.owens.edu.programservice.dto;

import com.owens.edu.programservice.entity.CurriculumItem;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumDto {
    private Long programId;
    private String description;
    private Set<CurriculumItem> curriculumItems;
}
