package com.owens.edu.commons.dto;

import com.owens.edu.commons.constants.ApiError;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
  private LocalDateTime timestamp;
  private String message;
  private String path;
  private ApiError errorCode;
}
