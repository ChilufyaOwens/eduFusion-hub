package com.owens.edu.studentservice.entity;

import com.owens.edu.studentservice.constants.Gender;
import com.owens.edu.studentservice.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_student")
@Entity
public class Student extends Auditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(unique = true)
    private String studentNumber;
    private String firstname;
    private String otherName;
    private String lastname;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private String email;
    private String contactNumber;
    private String identificationNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String nationality;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private Set<EmergencyContact> emergencyContacts;

    public Student(Long id,
                   String studentNumber,
                   String firstname,
                   String otherName,
                   String lastname,
                   LocalDate dob,
                   Gender gender,
                   String identificationNumber,
                   String nationality,
                   String contactNumber,
                   String email,
                   Status status
    ){
        this.id = id;
        this.studentNumber = studentNumber;
        this.firstname = firstname;
        this.otherName = otherName;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.contactNumber = contactNumber;
        this.identificationNumber = identificationNumber;
        this.status = status;
        this.nationality = nationality;
    }
}
