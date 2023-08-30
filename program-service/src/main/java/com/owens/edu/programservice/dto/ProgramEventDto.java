package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramEventDto {
    private String name;
    private Long programId;
    private LocalDate eventDate;
}
