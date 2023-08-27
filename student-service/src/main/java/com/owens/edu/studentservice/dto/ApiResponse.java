package com.owens.edu.studentservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean isSuccess;
    private String message;
    Object data;
}
