package com.owens.edu.studentservice.entity;

import com.owens.edu.studentservice.constants.Gender;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_emergency_contact")
@Entity
public class EmergencyContact extends Auditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String otherName;
    private String lastname;
    private Gender gender;
    private String contactNumber;
    private String email;
    private String relationship;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @ToString.Exclude
    private Student student;

}
