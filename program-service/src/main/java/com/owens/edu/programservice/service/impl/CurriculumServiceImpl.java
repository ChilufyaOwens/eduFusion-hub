package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.CurriculumRequest;
import com.owens.edu.programservice.dto.CurriculumItemDto;
import com.owens.edu.programservice.dto.CurriculumResponse;
import com.owens.edu.programservice.dto.mapper.CurriculumMapper;
import com.owens.edu.programservice.dto.mapper.CurriculumResponseMapper;
import com.owens.edu.programservice.entity.Curriculum;
import com.owens.edu.programservice.entity.CurriculumItem;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.exception.ProgramAlreadyExistException;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.CurriculumRepository;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.CurriculumService;
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

    @Override
    public CurriculumResponse createProgramCurriculum(CurriculumRequest request) {
        log.info("Creating program curriculum for program with Id: {}", request.getProgramId());

        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("Program with Id: '%s' not found", request.getProgramId())));

        curriculumRepository.findCurriculumByProgram(program)
                .ifPresent(curriculum -> {
                    throw new ProgramAlreadyExistException(
                            String.format("Curriculum already exists for program with name: '%s'", program.getName()));
                });

        Curriculum curriculum = curriculumMapper.toEntity(request);
        curriculum.setProgram(program);

        Curriculum savedCurriculum = curriculumRepository.save(curriculum);

        log.info("Curriculum with id: {} saved for {} program", savedCurriculum.getId(), program.getName());
        return curriculumResponseMapper.toDto(savedCurriculum);
    }

    @Override
    public CurriculumResponse getCurriculumByProgramId(Long programId) {
        log.info("Getting curriculum for program with Id: {}", programId);

        //Check if program exists
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(String.format(
                        "Program with Id: '%s' not found", programId
                )));

        Curriculum curriculum = curriculumRepository.findCurriculumByProgram(program)
                .orElseThrow(() -> new ProgramNotFoundException("Curriculum for '%s' program not found"));
        log.info("Fetched program curriculum for program with Id: {}", program);
        return curriculumResponseMapper.toDto(curriculum);
    }

    @Override
    public CurriculumResponse getCurriculumById(Long curriculumId) {
        log.info("Fetching program curriculum with Id: {}", curriculumId);

        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new ProgramNotFoundException("Curriculum with Id: '%s' not found"));

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
                        String.format("Curriculum with Id: '%s' not found.", curriculumId)
                ));

        if(!(Objects.equals(curriculum.getProgram().getId(), updateRequest.getProgramId()))){
            Program program = programRepository.findById(updateRequest.getProgramId())
                    .orElseThrow(() -> new ProgramNotFoundException(
                            String.format("Program with Id: '%s' not found.", updateRequest.getProgramId())
                    ));
            curriculum.setProgram(program);
        }

        curriculum.setDescription(updateRequest.getDescription());
        Set<CurriculumItem> curriculumItems = curriculum.getCurriculumItems()
                .stream()
                .map(item -> {
                    // Find the corresponding update request item by name (if it exists)
                    Optional<CurriculumItemDto> matchingUpdateItem = updateRequest.getCurriculumItems()
                            .stream()
                            .filter(updateItem -> updateItem.getName().equals(item.getName()))
                            .findFirst();

                    // If a matching update request item exists, update the curriculum item
                    matchingUpdateItem.ifPresent(updateItem -> {
                        item.setCourseId(updateItem.getCourseId());
                        item.setCourseName(updateItem.getCourseName());
                        item.setDescription(updateItem.getDescription());
                        item.setOrder(updateItem.getOrder());
                        item.setModuleCode(updateItem.getModuleCode());
                    });

                    return item;
                })
                .collect(Collectors.toSet());
        curriculum.setCurriculumItems(curriculumItems);

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
                        String.format("Curriculum with curriculumId: '%s', not found.", curriculumId)
                ));

        curriculumRepository.delete(curriculum);

        log.info("Deleted curriculum with id: {}", curriculumId);
        return String.format("Curriculum with curriculumId: '%s', deleted successfully.", curriculumId);
    }
}
