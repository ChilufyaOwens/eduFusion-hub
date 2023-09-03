package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.ProgramEventRequest;
import com.owens.edu.programservice.dto.ProgramEventResponse;
import com.owens.edu.programservice.service.ProgramEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PROGRAM_EVENT_SERVICE")
public class ProgramEventServiceImpl implements ProgramEventService {
    @Override
    public ProgramEventResponse createProgramEvent(ProgramEventRequest request) {
        return null;
    }

    @Override
    public ProgramEventResponse getProgramEventByProgramId(Long programId) {
        return null;
    }

    @Override
    public ProgramEventResponse getProgramEventById(Long eventId) {
        return null;
    }

    @Override
    public List<ProgramEventResponse> getAllEvents() {
        return null;
    }

    @Override
    public ProgramEventResponse updateProgramEventById(Long eventId, ProgramEventRequest request) {
        return null;
    }

    @Override
    public ProgramEventResponse disableProgramEventById(Long eventId) {
        return null;
    }

    @Override
    public String deleteProgramEventById(Long eventId) {
        return null;
    }
}
