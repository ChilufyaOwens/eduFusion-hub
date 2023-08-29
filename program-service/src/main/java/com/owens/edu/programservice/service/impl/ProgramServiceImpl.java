package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.constants.Status;
import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.controller.request.UpdateProgramRequest;
import com.owens.edu.programservice.dto.Course;
import com.owens.edu.programservice.dto.ProgramResponse;
import com.owens.edu.programservice.dto.mapper.ProgramResponseMapper;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PROGRAM_SERVICE")
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramResponseMapper programResponseMapper;

    @Override
    public ProgramResponse createProgram(CreateProgramRequest request) {
        log.info("Creating program with name: {}", request.getName());
        //Check if a program with a given name exists
        Optional<Program> programByNameAndStatus = programRepository.
                findProgramByNameAndStatus(request.getName(), Status.ACTIVE);
        return null;
    }

    @Override
    public ProgramResponse getProgramById(Long programId) {
        return null;
    }

    @Override
    public List<ProgramResponse> getPrograms() {
        return null;
    }

    @Override
    public List<Course> getCoursesByProgramId(Long programId) {
        return null;
    }

    @Override
    public ProgramResponse updateProgram(Long programId, UpdateProgramRequest request) {
        return null;
    }

    @Override
    public String deleteProgramById(Long programId) {
        return null;
    }
}
