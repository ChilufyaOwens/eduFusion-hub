package com.owens.edu.programservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramEventResponse {
    private String name;
    private String description;
    private LocalDate eventDate;
    private LocalDate eventEndDate;
    private String status;
    private Long programId;
    private LocalDate createdAt;
}
