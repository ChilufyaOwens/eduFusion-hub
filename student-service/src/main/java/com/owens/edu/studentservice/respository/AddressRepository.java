package com.owens.edu.studentservice.respository;

import com.owens.edu.studentservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
