package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.ProgramEventRequest;
import com.owens.edu.programservice.dto.ProgramEventResponse;

import java.util.List;

public interface ProgramEventService {
    ProgramEventResponse createProgramEvent(ProgramEventRequest request);
    ProgramEventResponse getProgramEventByProgramId(Long programId);
    ProgramEventResponse getProgramEventById(Long eventId);
    List<ProgramEventResponse> getAllEvents();
    ProgramEventResponse updateProgramEventById(Long eventId, ProgramEventRequest request);
    ProgramEventResponse disableProgramEventById(Long eventId);
    String deleteProgramEventById(Long eventId);
}
