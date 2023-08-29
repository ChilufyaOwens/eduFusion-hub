package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.constants.Status;
import com.owens.edu.programservice.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Program> findProgramByNameAndStatus(String programName, Status status);
}
