package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.RequiredCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredCourseRepository extends JpaRepository<RequiredCourse, Long> {
}
