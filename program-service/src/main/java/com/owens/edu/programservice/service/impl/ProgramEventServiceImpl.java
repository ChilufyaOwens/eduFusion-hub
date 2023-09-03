package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.constants.Status;
import com.owens.edu.programservice.controller.request.ProgramEventRequest;
import com.owens.edu.programservice.dto.ProgramEventResponse;
import com.owens.edu.programservice.dto.mapper.ProgramEventMapper;
import com.owens.edu.programservice.dto.mapper.ProgramEventResponseMapper;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.entity.ProgramEvent;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.ProgramEventRepository;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.ProgramEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PROGRAM_EVENT_SERVICE")
public class ProgramEventServiceImpl implements ProgramEventService {

    private final ProgramEventRepository programEventRepository;
    private final ProgramEventResponseMapper programEventResponseMapper;
    private final ProgramEventMapper programEventMapper;
    private final ProgramRepository programRepository;
    @Override
    public ProgramEventResponse createProgramEvent(ProgramEventRequest request) {
        log.info("Creating program event with name: {}, for program with Id: {}",
                request.getName(), request.getProgramId());

        //Check if program exists
        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("Program with Id: '%s' not found.", request.getProgramId())
                ));

        ProgramEvent programEvent = programEventMapper.toEntity(request);
        programEvent.setProgram(program);
        programEvent.setStatus(Status.PENDING_APPROVAL);

        ProgramEvent savedProgramEvent = programEventRepository.save(programEvent);

        ProgramEventResponse programEventResponse = programEventResponseMapper.toDto(savedProgramEvent);
        programEventResponse.setProgramId(program.getId());

        log.info("Program event created with event Id: {} and name: {}", savedProgramEvent.getId(),
                savedProgramEvent.getName());
        return programEventResponse;
    }

    @Override
    public ProgramEventResponse getProgramEventByProgramId(Long programId) {

        log.info("Fetching program program event with Id: {}", programId);

        //Check if program exists
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("Program with Id: '%s' not found.", programId)
                ));

        ProgramEvent programEvent = programEventRepository.findProgramEventByProgram(program)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("ProgramEvent with programId: '%s' not found.", program.getId())));

        ProgramEventResponse programEventResponse = programEventResponseMapper.toDto(programEvent);
        programEventResponse.setProgramId(programId);

        log.info("Fetched program event for program with Id: {}", programId);
        return programEventResponse;
    }

    @Override
    public ProgramEventResponse getProgramEventById(Long eventId) {

        log.info("Fetching program event with Id: {}", eventId);

        ProgramEvent programEvent = programEventRepository.findById(eventId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("ProgramEvent with Id: '%s' not found.", eventId)
                ));

        ProgramEventResponse programEventResponse = programEventResponseMapper.toDto(programEvent);

        programEventResponse.setProgramId(programEvent.getProgram().getId());
        log.info("Fetched program event with eventId: {}", eventId);

        return programEventResponse;
    }

    @Override
    public List<ProgramEventResponse> getAllEvents() {
        log.info("Fetching all program's events");

        List<ProgramEventResponse> programEvents = programEventRepository.findAll()
                .stream()
                .map(programEventResponseMapper::toDto)
                .map(programEventResponse -> {
                    programEventResponse.setProgramId(programEventResponse.getProgramId());
                    return programEventResponse;
                }).toList();

        log.info("Fetched all Programs events");
        return programEvents;

    }

    @Override
    public ProgramEventResponse updateProgramEventById(Long eventId, ProgramEventRequest request) {

        log.info("Updating program's event with Id: {}", eventId);

        ProgramEvent existingProgramEvent = programEventRepository.findById(eventId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("ProgramEvent with Id: '%s' not found", eventId)
                ));

        existingProgramEvent.setEventDate(LocalDate.parse(request.getEventDate()));
        existingProgramEvent.setEventEndDate(LocalDate.parse(request.getEventEndDate()));
        existingProgramEvent.setName(request.getName());
        existingProgramEvent.setDescription(request.getDescription());

        programRepository.findById(request.getProgramId()).ifPresentOrElse(
                existingProgramEvent::setProgram,
                () -> {
                    throw new ProgramNotFoundException(
                            String.format("Program with Id: '%s' not found", request.getProgramId())
                    );
                }
        );

        ProgramEvent updatedProgramEvent = programEventRepository.save(existingProgramEvent);

        ProgramEventResponse programEventResponse = programEventResponseMapper.toDto(updatedProgramEvent);
        programEventResponse.setProgramId(request.getProgramId());

        log.info("Program event with Id: {} updated.", eventId);
        return programEventResponse;

    }

    @Override
    public ProgramEventResponse cancelProgramEventById(Long eventId) {

        log.info("Cancelling program event with Id: {}", eventId);

        //Check if the event exists
        ProgramEvent programEvent = programEventRepository.findById(eventId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("Program event with Id: '%s' not found.", eventId)
                ));

        programEvent.setStatus(Status.CANCELLED);
        ProgramEvent cancelEventRequest = programEventRepository.save(programEvent);

        ProgramEventResponse programEventResponse = programEventResponseMapper.toDto(cancelEventRequest);
        programEventResponse.setProgramId(cancelEventRequest.getProgram().getId());

        log.info("Program event cancel request sent for approval");
        return programEventResponse;

    }

    @Override
    public String deleteProgramEventById(Long eventId) {
        log.info("Deleting program event with Id: {}", eventId);

        //Check if program event exists
        ProgramEvent programEvent = programEventRepository.findById(eventId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("Program event with Id: '%s' not found.", eventId)
                ));
        programEventRepository.delete(programEvent);

        log.info("Program event with Id: {} deleted.", eventId);

        return String.format("Program event with Id: '%s' deleted successfully", eventId);
    }
}
