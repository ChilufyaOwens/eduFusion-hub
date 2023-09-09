package com.owens.edu.programservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_curriculum")
@Entity
public class Curriculum extends ProgramJpaAudit<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    @ToString.Exclude
    private Program program;
    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Module> modules;

    public Curriculum(
            String name,
            String description,
            Program program
    ){
        this.name = name;
        this.description = description;
        this.program = program;
    }
}
