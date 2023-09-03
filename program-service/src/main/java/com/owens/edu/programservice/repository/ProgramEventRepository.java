package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.entity.ProgramEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramEventRepository extends JpaRepository<ProgramEvent, Long> {

    Optional<ProgramEvent> findProgramEventByProgram(Program program);
}
