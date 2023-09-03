package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.LearningOutcome;
import com.owens.edu.programservice.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LearningOutcomeRepository extends JpaRepository<LearningOutcome, Long> {

    Optional<LearningOutcome> findLearningOutcomeByName(String learningOutcomeName);

    Optional<LearningOutcome> findLearningOutcomeByProgram(Program program);
}
