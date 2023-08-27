package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.controller.request.UpdateProgramRequest;
import com.owens.edu.programservice.dto.Course;
import com.owens.edu.programservice.dto.CreateProgramResponse;
import com.owens.edu.programservice.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PROGRAM_SERVICE")
public class ProgramServiceImpl implements ProgramService {
    @Override
    public CreateProgramResponse createProgram(CreateProgramRequest request) {
        return null;
    }

    @Override
    public CreateProgramResponse getProgramById(Long programId) {
        return null;
    }

    @Override
    public List<CreateProgramResponse> getPrograms() {
        return null;
    }

    @Override
    public List<Course> getCoursesByProgramId(Long programId) {
        return null;
    }

    @Override
    public CreateProgramResponse updateProgram(Long programId, UpdateProgramRequest request) {
        return null;
    }

    @Override
    public String deleteProgramById(Long programId) {
        return null;
    }
}
