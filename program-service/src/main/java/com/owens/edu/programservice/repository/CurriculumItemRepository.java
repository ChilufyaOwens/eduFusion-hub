package com.owens.edu.programservice.repository;

import com.owens.edu.programservice.entity.CurriculumItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumItemRepository extends JpaRepository<CurriculumItem, Long> {
}
