package com.owens.edu.studentservice.respository;

import com.owens.edu.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmail(String studentEmail);

    Optional<Student> findStudentByStudentNumber(String studentNumber);
}
