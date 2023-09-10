package com.owens.edu.programservice.dto;
import com.owens.edu.programservice.constants.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleResponse {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isComplete;
    private Integer failedCourseCount;
    private Integer order;
    private Status status;
    private CurriculumResponse curriculum;
    private LocalDateTime createdAt;

}
