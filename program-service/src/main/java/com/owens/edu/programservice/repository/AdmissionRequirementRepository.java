package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.AdmissionRequirement;
import com.owens.edu.programservice.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRequirementRepository extends JpaRepository<AdmissionRequirement, Long> {
    List<AdmissionRequirement> findAdmissionRequirementsByProgram(Program program);
}
