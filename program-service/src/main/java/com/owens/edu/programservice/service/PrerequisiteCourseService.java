package com.owens.edu.programservice.service;

import com.owens.edu.programservice.dto.PrerequisiteCourseDto;
import com.owens.edu.programservice.entity.AdmissionRequirement;
import com.owens.edu.programservice.entity.PrerequisiteCourse;

import java.util.Set;

public sealed interface PrerequisiteCourseService permits PrerequisiteCourseServiceImpl{
    Set<PrerequisiteCourseDto> savePrerequisiteCourses(Set<PrerequisiteCourse> prerequisiteCourses,
                                                       AdmissionRequirement admissionRequirement);
}
