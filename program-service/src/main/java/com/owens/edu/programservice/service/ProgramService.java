package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.controller.request.UpdateProgramRequest;
import com.owens.edu.programservice.dto.Course;
import com.owens.edu.programservice.dto.CreateProgramResponse;

import java.util.List;

public interface ProgramService {
    CreateProgramResponse createProgram(CreateProgramRequest request);
    CreateProgramResponse getProgramById(Long programId);
    List<CreateProgramResponse> getPrograms();
    List<Course> getCoursesByProgramId(Long programId);

    CreateProgramResponse updateProgram(Long programId, UpdateProgramRequest request);
    String deleteProgramById(Long programId);

}
