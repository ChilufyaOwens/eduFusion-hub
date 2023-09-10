package com.owens.edu.programservice.service;

import com.owens.edu.programservice.utils.AppMessage;
import com.owens.edu.programservice.constants.Status;
import com.owens.edu.programservice.controller.request.CourseRequest;
import com.owens.edu.programservice.controller.request.ModuleRequest;
import com.owens.edu.programservice.dto.CourseResponse;
import com.owens.edu.programservice.dto.ModuleResponse;
import com.owens.edu.programservice.dto.mapper.CourseMapper;
import com.owens.edu.programservice.dto.mapper.CourseResponseMapper;
import com.owens.edu.programservice.dto.mapper.ModuleMapper;
import com.owens.edu.programservice.dto.mapper.ModuleResponseMapper;
import com.owens.edu.programservice.entity.Course;
import com.owens.edu.programservice.entity.Curriculum;
import com.owens.edu.programservice.entity.Module;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.CourseRepository;
import com.owens.edu.programservice.repository.CurriculumRepository;
import com.owens.edu.programservice.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "MODULE_SERVICE")
public final class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CurriculumRepository curriculumRepository;
    private final ModuleMapper mapper;
    private final ModuleResponseMapper responseMapper;
    private final CourseMapper courseMapper;
    private final CourseResponseMapper courseResponseMapper;
    private final CourseRepository courseRepository;
    @Override
    public Set<Module> saveModules(Set<Module> modules, Curriculum curriculum) {

        log.info("Saving curriculum modules");

        return modules.stream()
                .map(module -> {
                    module.setCurriculum(curriculum);
                    return moduleRepository.save(module);
                })
                .collect(Collectors.toSet());
    }

    /**
     * addCurriculumModule method chained Operations: or combined the operations into a chain of methods.
     * If the curriculum is present, the module will be created, associated with the curriculum, saved,
     * logged, and then converted to a DTO. If the curriculum is not present, a ProgramNotFoundException will be thrown.
     * @param request add module request
     * @return module response
     */
    @Override
    public ModuleResponse addCurriculumModule(ModuleRequest request) {
        return curriculumRepository.findById(request.getCurriculumId())
                .map(curriculum -> {
                    log.info("Adding curriculum module for curriculum with id: {}", curriculum.getId());
                    Module module = mapper.toEntity(request);
                    module.setCurriculum(curriculum);
                    module.setStatus(Status.UPCOMING);
                    Module addedModule = moduleRepository.save(module);
                    log.info("Curriculum module for curriculum with id: {}, added", addedModule.getCurriculum().getId());
                    return responseMapper.toDto(addedModule);
                })
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.CURRICULUM_NOT_FOUND_ERROR_MESSAGE, request.getCurriculumId())
                ));
    }

    @Override
    public Set<CourseResponse> addModuleCourses(CourseRequest request) {
        log.info("Adding courses to the module with id: {}", request.getModuleId());

        //Check if module exists
        Module module = moduleRepository.findById(request.getModuleId())
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.MODULE_NOT_FOUND_ERROR_MESSAGE, request.getModuleId())
                ));

        Set<CourseResponse> responses = request.getCourses().stream()
                .map(courseDto -> {
                    Course courseEntity = courseMapper.toEntity(courseDto);
                    courseEntity.setModule(module);
                    return courseRepository.save(courseEntity);
                })
                .map(courseResponseMapper::toDto)
                .collect(Collectors.toSet());

        log.info("Added all module courses");

        return responses;
    }

    /**
     *fetchCurriculumModuleById method Chained Operations: combined the operations into a chain of methods.
     * If the module is present, it will be fetched, logged, and then converted to a DTO. If the module is not present,
     * a ProgramNotFoundException will be thrown.
     * @param moduleId curriculum moduleId
     * @return curriculum module response
     */
    @Override
    public ModuleResponse fetchCurriculumModuleById(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .map(module -> {
                    log.info("Fetching curriculum module with id: {}", moduleId);
                    log.info("Curriculum module with id: {}, fetched", moduleId);
                    return responseMapper.toDto(module);
                })
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.MODULE_NOT_FOUND_ERROR_MESSAGE, moduleId)
                ));
    }

    /**
     * deleteCurriculumModuleById method's Chained Operations: combined the operations into a chain of methods.
     * This ensures that if the module is present, it will be deleted, and the success message will be returned.
     * If the module is not present, a ProgramNotFoundException will be thrown.
     * @param moduleId curriculum moduleId
     * @return message string
     */
    @Override
    public String deleteCurriculumModuleById(Long moduleId) {
        return moduleRepository.findById(moduleId)
                .map(module -> {
                    log.info("Removing or deleting curriculum module with id: {}", moduleId);
                    moduleRepository.delete(module);
                    log.info("Curriculum module with id: {} deleted.", moduleId);
                    return String.format("Curriculum module with id: '%s' deleted successfully", moduleId);
                })
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.MODULE_NOT_FOUND_ERROR_MESSAGE, moduleId)
                ));
    }

    @Override
    public String removeCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    log.info("Removing course from curriculum module with id: {}", courseId);
                    courseRepository.delete(course);

                    log.info("Module course with id: {} deleted", courseId);
                    return String.format("Module course with id: '%s' deleted successfully", courseId);
                }).orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.MODULE_NOT_FOUND_ERROR_MESSAGE, courseId)
                ));
    }
}
