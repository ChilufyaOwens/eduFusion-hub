package com.owens.edu.programservice.entity;

import com.owens.edu.programservice.constants.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_module")
@Entity
public class Module extends ProgramJpaAudit<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private String name;
    private String description;
    private String moduleCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isComplete;
    private Integer failedCourseCount;
    @Column(name = "module_order")
    private Integer order;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    @ToString.Exclude
    private Curriculum curriculum;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Course> courses;
}
