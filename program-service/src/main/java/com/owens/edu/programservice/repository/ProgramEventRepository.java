package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.ProgramEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramEventRepository extends JpaRepository<ProgramEvent, Long> {
}
