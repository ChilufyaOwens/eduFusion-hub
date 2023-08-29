package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
