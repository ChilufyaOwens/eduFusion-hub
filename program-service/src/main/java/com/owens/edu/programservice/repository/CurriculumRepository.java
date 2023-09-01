package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.Curriculum;
import com.owens.edu.programservice.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    Optional<Curriculum> findCurriculumByProgram(Program program);
}
