package com.owens.edu.commons.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean isSuccess;
    private String message;
    private HttpStatus status;
    Object data;
}
