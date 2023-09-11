package com.owens.edu.programservice.entity;

import com.owens.edu.programservice.constants.RequirementType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_admission_requirements")
@Entity
public class AdmissionRequirement extends ProgramJpaAudit<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    @ToString.Exclude
    private Program program;
    private String requirementDescription;
    @Enumerated(EnumType.STRING)
    private RequirementType requirementType;
    private Integer minimumCredits;
    @OneToMany(mappedBy = "admissionRequirement", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<PrerequisiteCourse> prerequisiteCourses;

    public AdmissionRequirement(Program program,
                                String requirementDescription,
                                Integer minimumCredits,
                                RequirementType requirementType
                                ){
        this.program = program;
        this.requirementDescription = requirementDescription;
        this.minimumCredits = minimumCredits;
        this.requirementType = requirementType;

    }
}
