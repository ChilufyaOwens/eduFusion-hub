package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.utils.AppMessage;
import com.owens.edu.programservice.constants.DegreeType;
import com.owens.edu.programservice.constants.Status;
import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.controller.request.UpdateProgramRequest;
import com.owens.edu.programservice.dto.ProgramResponse;
import com.owens.edu.programservice.dto.mapper.ProgramResponseMapper;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.exception.ProgramAlreadyExistException;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        programRepository.
                findProgramByNameAndStatus(request.getName(), Status.ACTIVE)
                .ifPresent(program -> {
                    throw new ProgramAlreadyExistException(
                            String.format(AppMessage.PROGRAM_ALREADY_EXISTS_ERROR_MESSAGE,
                                    request.getName(),
                                    request.getDegreeType()));
                });

        Program savedProgram = programRepository.save(
                getBuildProgram(request)
        );

        log.info("Program with id: {} and name: {}, created.", savedProgram.getId(), savedProgram.getName());

        return programResponseMapper.toDto(savedProgram);
    }

    private static Program getBuildProgram(CreateProgramRequest request) {
        return Program.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(LocalDate.parse(request.getStartDate()))
                .endDate(LocalDate.parse(request.getEndDate()))
                .coordinatorId(request.getCoordinatorId())
                .coordinatorName(request.getCoordinatorName())
                .departmentId(request.getDepartmentId())
                .departmentName(request.getDepartmentName())
                .degreeType(DegreeType.valueOf(request.getDegreeType()))
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public ProgramResponse getProgramById(Long programId) {
        log.info("Fetching created program with Id: {}", programId);
        //Check if program exists
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(String
                        .format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)));
        log.info("Fetched program with id: {}, and name: {}", programId, program.getName());
        return programResponseMapper.toDto(program);
    }

    @Override
    public List<ProgramResponse> getPrograms() {
        log.info("Fetching all created programs for the institution.");

        return programRepository.findAll()
                .stream()
                .map(programResponseMapper::toDto)
                .toList();
    }


    /**
     * updateProgram method updates program details
     * @param programId program been updated
     * @param request updated details
     * @return program update response
     */
    @Override
    public ProgramResponse updateProgram(Long programId, UpdateProgramRequest request) {
        log.info("Updating program details for program with Id {}", programId);
        //Check if program with programId exists
        Program existingProgram = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(String
                        .format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)));

        Program updatedProgram = programRepository.save(
                updatedProgramDetails(request, existingProgram)
        );

        log.info("Program with Id: {} updated.", programId);
        return programResponseMapper.toDto(updatedProgram);
    }

    private static Program updatedProgramDetails(UpdateProgramRequest request, Program existingProgram) {
        existingProgram.setStartDate(request.getStartDate());
        existingProgram.setEndDate(request.getEndDate());
        existingProgram.setCoordinatorId(request.getCoordinatorId());
        existingProgram.setCoordinatorName(request.getCoordinatorName());
        existingProgram.setDepartmentId(request.getDepartmentId());
        existingProgram.setDepartmentName(request.getDepartmentName());
        existingProgram.setDegreeType(DegreeType.valueOf(request.getDegreeType()));
        return existingProgram;
    }

    /**
     * deleteProgramById method deletes an existing program from the school database permanently.
     * This functionality will on be performed only by users with the right to request a delete program approved
     * @param programId program been deleted permanently
     * @return String message of the operation
     */
    @Override
    public String deleteProgramById(Long programId) {
        log.info("Deleting program with id: {}", programId);
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)
                ));
        //Delete this program.
        programRepository.delete(program);
        log.info("Program with Id: {} and name: {}, deleted.", program.getId(), program.getName());

        return String.format("Program with name: %s deleted", program.getName());
    }

    /**
     * discontinueProgram method disables the program from been accessed by student
     * @param programId discontinued program
     * @return response message after disabling program
     */
    @Override
    public String discontinueProgram(Long programId) {
        log.info("Discontinue program with Id: {}", programId);
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)
                ));
        program.setStatus(Status.INACTIVE);
        programRepository.save(program);

        log.info("Program with Id: {} and name: {}, discontinued.", program.getId(), program.getName());

        return String.format("Program with name: %s, discontinued.", program.getName());
    }
}
