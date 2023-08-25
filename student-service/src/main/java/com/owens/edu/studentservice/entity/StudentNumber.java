package com.owens.edu.studentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_student_number")
@Entity
public class StudentNumber extends Auditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Size(max = 13, min = 13)
    @Column(nullable = false, unique = true)
    private String studentNumber;
}
