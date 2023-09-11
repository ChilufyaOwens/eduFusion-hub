package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.constants.RequirementType;
import com.owens.edu.programservice.controller.request.AdmissionRequirementRequest;
import com.owens.edu.programservice.dto.AdmissionRequirementResponse;
import com.owens.edu.programservice.dto.PrerequisiteCourseDto;
import com.owens.edu.programservice.dto.mapper.AdmissionRequirementMapper;
import com.owens.edu.programservice.dto.mapper.AdmissionRequirementResponseMapper;
import com.owens.edu.programservice.dto.mapper.PrerequisiteCourseMapper;
import com.owens.edu.programservice.entity.AdmissionRequirement;
import com.owens.edu.programservice.entity.PrerequisiteCourse;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.AdmissionRequirementRepository;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.AdmissionRequirementService;
import com.owens.edu.programservice.service.PrerequisiteCourseService;
import com.owens.edu.programservice.utils.AppMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ADMISSION_REQUIREMENT_SERVICE")
public class AdmissionRequirementServiceImpl implements AdmissionRequirementService {

    private final AdmissionRequirementRepository admissionRequirementRepository;
    private final AdmissionRequirementMapper admissionRequirementMapper;
    private final AdmissionRequirementResponseMapper admissionRequirementResponseMapper;
    private final ProgramRepository programRepository;
    private final PrerequisiteCourseMapper prerequisiteCourseMapper;
    private final PrerequisiteCourseService prerequisiteCourseService;

    @Override
    public AdmissionRequirementResponse createProgramAdmissionRequirement(AdmissionRequirementRequest request) {
        Long programId = request.getProgramId();
        log.info("Creating program's admissions requirement with programId: {}", programId);

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new ProgramNotFoundException(String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)));

        AdmissionRequirement admissionRequirement = admissionRequirementMapper.toEntity(request);
        admissionRequirement.setProgram(program);

        AdmissionRequirement savedAdmissionRequirement = admissionRequirementRepository.save(
                new AdmissionRequirement(
                        admissionRequirement.getProgram(),
                        admissionRequirement.getRequirementDescription(),
                        admissionRequirement.getMinimumCredits(),
                        admissionRequirement.getRequirementType()
                )
        );

        //Save prerequisite courses for admission requirements
        Set<PrerequisiteCourseDto> prerequisiteCourses = prerequisiteCourseService.savePrerequisiteCourses(
                admissionRequirement.getPrerequisiteCourses(), savedAdmissionRequirement);

        log.info("Program admission requirement created with Id: {}", savedAdmissionRequirement.getId());

        AdmissionRequirementResponse requirementResponse = admissionRequirementResponseMapper.toDto(savedAdmissionRequirement);
        requirementResponse.setProgramId(program.getId());
        requirementResponse.setRequiredCourses(prerequisiteCourses);

        return requirementResponse;
    }


    @Override
    public List<AdmissionRequirementResponse> getProgramAdmissionRequirementsById(Long programId) {

        log.info("Fetching all program's admissions requirement with programId: {}", programId);
        //Check if program exists
        Program program = programRepository.findById(programId).orElseThrow(() -> new ProgramNotFoundException(
                String.format(AppMessage.PROGRAM_NOT_FOUND_ERROR_MESSAGE, programId)
        ));

        List<AdmissionRequirement> admissionRequirementsByProgram = admissionRequirementRepository
                .findAdmissionRequirementsByProgram(program);
        log.info("Fetched all program's admissions requirement with programId: {}", programId);
        return buildAdmissionRequirementsResponse(admissionRequirementsByProgram);
    }

    private List<AdmissionRequirementResponse> buildAdmissionRequirementsResponse(
            List<AdmissionRequirement> admissionRequirementsByProgram) {

        log.info("Building admission requirement response object");

       return admissionRequirementsByProgram.stream()
                .map(admissionRequirementResponseMapper::toDto)
                .toList();
    }

    @Override
    public AdmissionRequirementResponse getAdmissionRequirementById(Long admissionRequirementsId) {

        log.info("Fetching Admission requirement with Id: {}", admissionRequirementsId);
        //Check if admissionRequirement exists
        AdmissionRequirement admissionRequirement = admissionRequirementRepository.findById(admissionRequirementsId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.ADMISSION_REQUIREMENT_NOT_FOUND_ERROR_MESSAGE, admissionRequirementsId)
                ));

        log.info("Fetched admission requirement with id: {}", admissionRequirementsId);

        return admissionRequirementResponseMapper.toDto(admissionRequirement);
    }

    @Override
    public List<AdmissionRequirementResponse> getAllAdmissionRequirement() {

        log.info("Fetching all admissions requirement for every program");
        //Get all created admission requirements for every program
        List<AdmissionRequirement> admissionRequirements = admissionRequirementRepository.findAll();

        log.info("Fetched all admissions requirement for every program");
        return buildAdmissionRequirementsResponse(admissionRequirements);
    }

    @Override
    public AdmissionRequirementResponse updateProgramAdmissionRequirement(
            Long admissionRequirementId,
            AdmissionRequirementRequest updateRequest) {

        log.info("Updating program admission requirement with Id: {}", admissionRequirementId);

        //Check if admissionRequirement exists
        AdmissionRequirement admissionRequirement = admissionRequirementRepository.findById(admissionRequirementId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.ADMISSION_REQUIREMENT_NOT_FOUND_ERROR_MESSAGE, admissionRequirementId)
                ));

        AdmissionRequirement updatedAdmissionRequirement = admissionRequirementRepository.save(
                updateAdmissionRequirement(admissionRequirement, updateRequest)
        );

        log.info("Updated program admission requirement with update request: {}", updateRequest);
        return admissionRequirementResponseMapper.toDto(updatedAdmissionRequirement);
    }

    private AdmissionRequirement updateAdmissionRequirement(
            AdmissionRequirement admissionRequirement,
            AdmissionRequirementRequest updateRequest) {

        log.info("Updating program's admission requirement properties with values from update request object");

        admissionRequirement.setRequirementType(RequirementType.valueOf(updateRequest.getRequirementType()));
        admissionRequirement.setMinimumCredits(updateRequest.getMinimumCredits());
        admissionRequirement.setRequirementDescription(updateRequest.getRequirementDescription());

        Set<PrerequisiteCourse> courses = updateRequest.getPrerequisiteCourses().stream()
                .map(prerequisiteCourseMapper::toEntity)
                .collect(Collectors.toSet());

        admissionRequirement.setPrerequisiteCourses(courses);

        Optional<Program> optionalProgram = programRepository.findById(updateRequest.getProgramId());
        if(optionalProgram.isPresent() && (admissionRequirement.getProgram() != optionalProgram.get())){
                admissionRequirement.setProgram(optionalProgram.get());
        }

        return admissionRequirement;
    }

    @Override
    public String deleteAdmissionRequirementById(Long admissionRequirementId) {

        log.info("Deleting program's admission requirement with Id: {}", admissionRequirementId);
        //Check if admissionRequirement exists
        AdmissionRequirement admissionRequirement = admissionRequirementRepository.findById(admissionRequirementId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format(AppMessage.ADMISSION_REQUIREMENT_NOT_FOUND_ERROR_MESSAGE, admissionRequirementId)
                ));

        //Delete admissionRequirement
        admissionRequirementRepository.delete(admissionRequirement);

        log.info("Program's admission requirement with Id: {} deleted.", admissionRequirementId);

        return String.format("AdmissionRequirement with Id: '%s' deleted.", admissionRequirementId);
    }
}
