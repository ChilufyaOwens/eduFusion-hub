package com.owens.edu.programservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_courses")
@Entity
public class Course extends ProgramJpaAudit<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private Long courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private String duration;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;
}
