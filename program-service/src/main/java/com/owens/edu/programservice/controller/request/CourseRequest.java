package com.owens.edu.programservice.controller.request;

import com.owens.edu.programservice.dto.CourseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotNull
    private Long moduleId;
    @NotEmpty
    private Set<CourseDto> courses;


}
