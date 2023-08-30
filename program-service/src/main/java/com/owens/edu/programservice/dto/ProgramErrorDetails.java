package com.owens.edu.programservice.dto;


import com.owens.edu.programservice.constants.ProgramApiError;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramErrorDetails {
  private LocalDateTime timestamp;
  private String message;
  private String path;
  private ProgramApiError errorCode;
}
