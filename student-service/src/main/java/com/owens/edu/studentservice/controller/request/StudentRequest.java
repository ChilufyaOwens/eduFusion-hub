package com.owens.edu.studentservice.controller.request;

import com.owens.edu.studentservice.dto.AddressDto;
import com.owens.edu.studentservice.dto.EmergencyContactDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank
    private String firstname;
    private String otherName;
    @NotBlank
    private String lastname;
    @NotNull
    private LocalDate dob;
    @NotBlank
    private String gender;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String contactNumber;
    @NotBlank
    private String identificationNumber;
    @NotBlank
    private String nationality;
    @NotNull
    private AddressDto address;
    private Set<EmergencyContactDto> emergencyContacts;
}
