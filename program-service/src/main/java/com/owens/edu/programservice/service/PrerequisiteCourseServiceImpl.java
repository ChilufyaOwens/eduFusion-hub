package com.owens.edu.programservice.service;

import com.owens.edu.programservice.dto.PrerequisiteCourseDto;
import com.owens.edu.programservice.dto.mapper.PrerequisiteCourseMapper;
import com.owens.edu.programservice.entity.AdmissionRequirement;
import com.owens.edu.programservice.entity.PrerequisiteCourse;
import com.owens.edu.programservice.repository.PrerequisiteCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PREREQUISITE_COURSES")
public final class PrerequisiteCourseServiceImpl implements PrerequisiteCourseService{

    private final PrerequisiteCourseRepository prerequisiteCourseRepository;
    private final PrerequisiteCourseMapper prerequisiteCourseMapper;

    /**
     * savePrerequisiteCourses methods saves prerequisite courses Before admission,
     * The minimum credits required for admission will be determined
     * by the specific program or degree they're applying for
     * @param prerequisiteCourses set of prerequisite courses
     * @return set of prerequisite courses
     */
    @Override
    public Set<PrerequisiteCourseDto> savePrerequisiteCourses(Set<PrerequisiteCourse> prerequisiteCourses,
                                                              AdmissionRequirement admissionRequirement) {
        log.info("Saving the minimum credits required for admission will be determined by the specific program.");

        return prerequisiteCourses.stream()
                .map(course -> {
                    course.setAdmissionRequirement(admissionRequirement);
                    PrerequisiteCourse prerequisiteCourse = prerequisiteCourseRepository.save(course);
                    return prerequisiteCourseMapper.toDto(prerequisiteCourse);
                })
                .collect(Collectors.toSet());
    }
}
