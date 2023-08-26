package com.owens.edu.courseservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_course")
@Entity
public class Course extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false )
    private Long id;
    private String name;
    private String code;
    private String description;
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;
    private Integer enrolledStudents;
    private Long programId;
    private String programName;
}
