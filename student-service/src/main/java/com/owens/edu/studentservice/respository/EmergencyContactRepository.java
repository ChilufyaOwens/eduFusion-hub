package com.owens.edu.studentservice.respository;

import com.owens.edu.studentservice.entity.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}
