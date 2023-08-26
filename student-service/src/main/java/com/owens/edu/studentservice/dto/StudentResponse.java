package com.owens.edu.studentservice.dto;

import com.owens.edu.studentservice.entity.Address;
import com.owens.edu.studentservice.entity.EmergencyContact;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String studentNumber;
    private String firstname;
    private String otherName;
    private String lastname;
    private LocalDate dob;
    private String identificationNumber;
    private String email;
    private String contactNumber;
    private String nationality;
    private String gender;
    private String status;
    private Address address;
    private Set<EmergencyContact> emergencyContacts;
    private LocalDateTime createdAt;

}
