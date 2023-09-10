package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.CourseRequest;
import com.owens.edu.programservice.controller.request.ModuleRequest;
import com.owens.edu.programservice.dto.CourseResponse;
import com.owens.edu.programservice.dto.ModuleResponse;
import com.owens.edu.programservice.entity.Curriculum;
import com.owens.edu.programservice.entity.Module;

import java.util.Set;

public sealed interface ModuleService permits ModuleServiceImpl {
    Set<Module> saveModules(Set<Module> modules, Curriculum curriculum);

    ModuleResponse addCurriculumModule(ModuleRequest request);

    Set<CourseResponse> addModuleCourses(CourseRequest request);

    ModuleResponse fetchCurriculumModuleById(Long moduleId);

    String deleteCurriculumModuleById(Long moduleId);

    String removeCourseById(Long courseId);
}
