package com.owens.edu.studentservice.dto;

import com.owens.edu.commons.constants.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactDto {
    @NotBlank
    private String firstname;
    private String otherName;
    @NotBlank
    private String lastname;
    private Gender gender;
    @NotBlank
    private String contactNumber;
    @NotBlank
    private String email;
    @NotBlank
    private String relationship;
}
