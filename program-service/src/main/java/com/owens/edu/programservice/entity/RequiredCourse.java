package com.owens.edu.programservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_required_course")
public class RequiredCourse extends ProgramJpaAudit<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "admission_requirements_id", referencedColumnName = "id")
    @ToString.Exclude
    private AdmissionRequirement admissionRequirement;

    private String name;
    private String description;
    private  Integer minimumCredits;

}