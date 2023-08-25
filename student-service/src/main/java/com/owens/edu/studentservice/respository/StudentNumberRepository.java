package com.owens.edu.studentservice.respository;

import com.owens.edu.studentservice.entity.StudentNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentNumberRepository extends JpaRepository<StudentNumber, Long> {
    Optional<StudentNumber> findStudentNumberByStudentNumber(String studentNumber);
}
