package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.CurriculumRequest;
import com.owens.edu.programservice.dto.CurriculumResponse;
import com.owens.edu.programservice.dto.ModuleDto;
import com.owens.edu.programservice.dto.mapper.CurriculumMapper;
import com.owens.edu.programservice.dto.mapper.CurriculumResponseMapper;
import com.owens.edu.programservice.entity.Curriculum;
import com.owens.edu.programservice.entity.Module;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.exception.ProgramAlreadyExistException;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.CurriculumRepository;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.CurriculumService;
import com.owens.edu.programservice.service.ModuleService;
import com.owens.edu.programservice.utils.AppMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "CURRICULUM_SERVICE")
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final CurriculumMapper curriculumMapper;
    private final CurriculumResponseMapper curriculumResponseMapper;
    private final ProgramRepository programRepository;
    private final ModuleService moduleService;

    @Override
    public CurriculumResponse createProgramCurriculum(CurriculumRequest request) {
        log.info("Creating program curriculum for program with Id: {}", request.getProgramId());

        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, request.getProgramId())));

        curriculumRepository.findCurriculumByProgram(program)
                .ifPresent(curriculum -> {
                    throw new ProgramAlreadyExistException(
                            String.format(AppMessage.CURRICULUM_ALREADY_EXISTS_ERROR_MESSAGE, program.getName()));
                });

        Curriculum curriculum = new Curriculum(
                request.getName(),
                request.getDescription(),
                program
        );

        Curriculum savedCurriculum = curriculumRepository.save(curriculum);

        log.info("Curriculum with id: {} saved for {} program", savedCurriculum.getId(), program.getName());

        //Check if there are modules
        if (request.getModules() != null) {
            Curriculum curriculumMapperEntity = curriculumMapper.toEntity(request);
            Set<Module> modules = moduleService.saveModules(curriculumMapperEntity.getModules(), savedCurriculum);
            savedCurriculum.setModules(modules);
        }
        return curriculumResponseMapper.toDto(savedCurriculum);
    }

    @Override
    public CurriculumResponse getCurriculumByProgramId(Long programId) {
        log.info("Getting curriculum for program with Id: {}", programId);

        //Check if program exists
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(String.format(
                        AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId
                )));

        Curriculum curriculum = curriculumRepository.findCurriculumByProgram(program)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.CURRICULUM_BY_PROGRAM_NOT_FOUND_ERROR_MESSAGE, program.getName())));
        log.info("Fetched program curriculum for program with Id: {}", program);
        return curriculumResponseMapper.toDto(curriculum);
    }

    @Override
    public CurriculumResponse getCurriculumById(Long curriculumId) {
        log.info("Fetching program curriculum with Id: {}", curriculumId);

        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.CURRICULUM_NOT_FOUND_ERROR_MESSAGE, curriculumId)));

        log.info("Fetched curriculum with Id: {}", curriculum.getId());
        return curriculumResponseMapper.toDto(curriculum);
    }

    @Override
    public List<CurriculumResponse> getAllCurricula() {
        log.info("Fetching curricula for all programs.");

        return curriculumRepository.findAll()
                .stream()
                .map(curriculumResponseMapper::toDto)
                .toList();
    }

    @Override
    public CurriculumResponse updateCurriculum(Long curriculumId, CurriculumRequest updateRequest) {
        log.info("Updating program curriculum with id: {}", curriculumId);

        //Check if curriculum is present
        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.CURRICULUM_NOT_FOUND_ERROR_MESSAGE, curriculumId)
                ));

        if (!(Objects.equals(curriculum.getProgram().getId(), updateRequest.getProgramId()))) {
            Program program = programRepository.findById(updateRequest.getProgramId())
                    .orElseThrow(() -> new ProgramNotFoundException(
                            String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, updateRequest.getProgramId())
                    ));
            curriculum.setProgram(program);
        }

        curriculum.setDescription(updateRequest.getDescription());
        Set<Module> modules = curriculum.getModules()
                .stream()
                .map(module -> {
                    // Find the corresponding update request item by name (if it exists)
                    Optional<ModuleDto> matchingUpdateItem = updateRequest.getModules()
                            .stream()
                            .filter(updateItem -> updateItem.getName().equals(module.getName()))
                            .findFirst();

                    // If a matching update request item exists, update the curriculum item
                    matchingUpdateItem.ifPresent(updateModule -> {
                        module.setDescription(updateModule.getDescription());
                        module.setOrder(updateModule.getOrder());
                        module.setModuleCode(updateModule.getModuleCode());
                    });

                    return module;
                })
                .collect(Collectors.toSet());
        curriculum.setModules(modules);

        Curriculum updatedCurriculum = curriculumRepository.save(curriculum);

        log.info("Updated program curriculum with Id: {}", curriculumId);

        return curriculumResponseMapper.toDto(updatedCurriculum);
    }

    @Override
    public String deleteCurriculumById(Long curriculumId) {
        log.info("Deleting curriculum with id: {}", curriculumId);

        //Check if the curriculum exists
        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.CURRICULUM_NOT_FOUND_ERROR_MESSAGE, curriculumId)
                ));

        curriculumRepository.delete(curriculum);

        log.info("Deleted curriculum with id: {}", curriculumId);
        return String.format("Curriculum with curriculumId: '%s', deleted successfully.", curriculumId);
    }
}
