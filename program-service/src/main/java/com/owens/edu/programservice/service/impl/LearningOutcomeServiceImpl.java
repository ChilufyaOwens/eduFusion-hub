package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.LearningOutcomeRequest;
import com.owens.edu.programservice.dto.LearningOutcomeResponse;
import com.owens.edu.programservice.dto.mapper.LearningOutcomeMapper;
import com.owens.edu.programservice.dto.mapper.LearningOutcomeResponseMapper;
import com.owens.edu.programservice.entity.LearningOutcome;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.exception.ProgramAlreadyExistException;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.LearningOutcomeRepository;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.LearningOutcomeService;
import com.owens.edu.programservice.utils.AppMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "LEARNING_OUTCOME_SERVICE")
public class LearningOutcomeServiceImpl implements LearningOutcomeService {

    private final LearningOutcomeRepository learningOutcomeRepository;
    private final ProgramRepository programRepository;
    private final LearningOutcomeMapper learningOutcomeMapper;
    private final LearningOutcomeResponseMapper learningOutcomeResponseMapper;

    @Override
    public LearningOutcomeResponse createLearningOutcome(LearningOutcomeRequest request) {
        log.info("Creating program learning outcome for program with Id: {}", request.getProgramId());

        //Check if program exists
        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, request.getProgramId())
                ));

        //Check if the name is unique

        learningOutcomeRepository.findLearningOutcomeByName(request.getName())
                .ifPresent(learningOutcome -> {
                    throw new ProgramAlreadyExistException(String.format(
                            "Program learning outcome with name: '%s' already exists", request.getName()));
                });

        LearningOutcome learningOutcome = learningOutcomeMapper.toEntity(request);
        learningOutcome.setProgram(program);

        LearningOutcome savedLearningOutcome = learningOutcomeRepository.save(learningOutcome);

        log.info("Learning outcome save for program with id: {} and name: {}", program.getId(), program.getName());

        return learningOutcomeResponseMapper.toDto(savedLearningOutcome);
    }

    @Override
    public LearningOutcomeResponse getLearningOutcomeByProgramId(Long programId) {

        log.info("Fetching learning outcome by programId: {}", programId);

        //Check if the program exists
        Program program = programRepository.findById(programId).orElseThrow(() -> new ProgramNotFoundException(
                String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)
        ));

        //Get learning outcome
        LearningOutcome learningOutcome = learningOutcomeRepository
                .findLearningOutcomeByProgram(program)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.LEARNING_OUTCOME_BY_PROGRAM_NOT_FOUND_ERROR_MESSAGE, program.getId())));

        LearningOutcomeResponse outcomeResponse = learningOutcomeResponseMapper.toDto(learningOutcome);
        outcomeResponse.setProgramId(programId);

        return outcomeResponse;
    }

    @Override
    public LearningOutcomeResponse getLearningOutcomeById(Long learningOutcomeId) {

        log.info("Fetching program learning outcome by learningOutcomeId: {}", learningOutcomeId);

        LearningOutcome learningOutcome = learningOutcomeRepository.findById(learningOutcomeId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.LEARNING_OUTCOME_NOT_FOUND_ERROR_MESSAGE, learningOutcomeId)));

        LearningOutcomeResponse outcomeResponse = learningOutcomeResponseMapper.toDto(learningOutcome);
        outcomeResponse.setProgramId(learningOutcome.getProgram().getId());

        log.info("Fetched program learning outcome with learningOutcomeId: {}", learningOutcomeId);

        return outcomeResponse;
    }

    @Override
    public Set<LearningOutcomeResponse> getAllLearningOutcome() {

        log.info("Fetching all program learning outcome");

        Set<LearningOutcomeResponse> responseSet = new HashSet<>();
        learningOutcomeRepository.findAll()
                .forEach(learningOutcome -> {
                    LearningOutcomeResponse outcomeResponse = learningOutcomeResponseMapper.toDto(learningOutcome);
                    outcomeResponse.setProgramId(learningOutcome.getProgram().getId());

                    responseSet.add(outcomeResponse);
                });

        log.info("Fetched all created program learning outcome");
        return responseSet;
    }

    @Override
    public LearningOutcomeResponse updateLearningOutcome(Long learningOutcomeId, LearningOutcomeRequest request) {
        log.info("Updating program's learning outcome with Id: {}", learningOutcomeId);

        //Check if the learning outcome exists
        LearningOutcome learningOutcome = learningOutcomeRepository.findById(learningOutcomeId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.LEARNING_OUTCOME_NOT_FOUND_ERROR_MESSAGE, learningOutcomeId)
                ));

        learningOutcome.setName(request.getName());
        learningOutcome.setDescription(request.getDescription());

        //Check if learning outcome program is the same as update request program
        if(!Objects.equals(learningOutcome.getProgram().getId(), request.getProgramId())){
            //Check if the program exists
            Program program = programRepository.findById(request.getProgramId())
                    .orElseThrow(() -> new ProgramNotFoundException(
                            String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, request.getProgramId())
                    ));
            learningOutcome.setProgram(program);
        }

        LearningOutcome updatedLearningOutcome = learningOutcomeRepository.save(learningOutcome);

        log.info("Program's learning outcome with Id: {} and name: {}, updated",
                learningOutcomeId, updatedLearningOutcome.getName());

        LearningOutcomeResponse outcomeResponse = learningOutcomeResponseMapper.toDto(updatedLearningOutcome);
        outcomeResponse.setProgramId(learningOutcome.getProgram().getId());

        return outcomeResponse;
    }

    @Override
    public String deleteLearningOutcomeById(Long learningOutcomeId) {
        log.info("Deleting program's learning outcome with Id: {}", learningOutcomeId);

        //Check if the learning outcome exists
        LearningOutcome learningOutcome = learningOutcomeRepository.findById(learningOutcomeId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.LEARNING_OUTCOME_NOT_FOUND_ERROR_MESSAGE, learningOutcomeId)
                ));

        learningOutcomeRepository.delete(learningOutcome);

        log.info("Program's learning outcome with Id: {} and name: {} deleted",
                learningOutcome.getId(), learningOutcome.getName());

        return String.format("'%s' program learning outcome deleted successfully.",
                learningOutcome.getProgram().getName());
    }
}
