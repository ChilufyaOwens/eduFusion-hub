package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
