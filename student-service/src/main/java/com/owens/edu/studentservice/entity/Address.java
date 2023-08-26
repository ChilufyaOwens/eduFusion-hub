package com.owens.edu.studentservice.entity;

import com.owens.edu.commons.entity.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_address")
@Entity
public class Address extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressLineOne;
    private String addressLineTwo;
    private String street;
    private String city;
    private String stateProvince;
    private String zipCode;
    private String country;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @ToString.Exclude
    private Student student;
}
