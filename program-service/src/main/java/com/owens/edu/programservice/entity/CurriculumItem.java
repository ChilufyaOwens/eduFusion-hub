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
@Table(name = "tbl_curriculum_item")
public class CurriculumItem extends ProgramJpaAudit<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String moduleCode;
    @Column(name = "item_order")
    private Integer order; // The order in which the item appears in the curriculum
    private Long courseId;
    private String courseName;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculum_id", referencedColumnName = "id")
    @ToString.Exclude
    private Curriculum curriculum;

}