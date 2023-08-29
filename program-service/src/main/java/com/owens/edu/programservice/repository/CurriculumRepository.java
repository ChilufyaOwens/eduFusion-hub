package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
