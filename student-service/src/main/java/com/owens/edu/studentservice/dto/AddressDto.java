package com.owens.edu.studentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotBlank
    private String addressLineOne;
    private String addressLineTwo;
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    private String zipCode;
    @NotBlank
    private String country;

}
