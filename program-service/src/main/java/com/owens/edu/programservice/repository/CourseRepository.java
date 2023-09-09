package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
