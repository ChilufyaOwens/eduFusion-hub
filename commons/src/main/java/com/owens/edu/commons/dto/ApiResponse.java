package com.owens.edu.commons.dto;

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
