package com.owens.edu.studentservice.controller.request;

import com.owens.edu.studentservice.dto.AddressDto;
import com.owens.edu.studentservice.dto.EmergencyContactDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequest {
    @NotBlank
    private String contactNumber;
    @NotNull
    private AddressDto address;
    @NotEmpty
    private Set<EmergencyContactDto> emergencyContacts;
}
