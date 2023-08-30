package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.controller.request.UpdateProgramRequest;
import com.owens.edu.programservice.dto.Course;
import com.owens.edu.programservice.dto.ProgramResponse;

import java.util.List;

public interface ProgramService {
    ProgramResponse createProgram(CreateProgramRequest request);
    ProgramResponse getProgramById(Long programId);
    List<ProgramResponse> getPrograms();
    ProgramResponse updateProgram(Long programId, UpdateProgramRequest request);
    String deleteProgramById(Long programId);
    String discontinueProgram(Long programId);

}
