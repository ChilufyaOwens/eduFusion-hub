package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.PrerequisiteCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredCourseRepository extends JpaRepository<PrerequisiteCourse, Long> {
}
