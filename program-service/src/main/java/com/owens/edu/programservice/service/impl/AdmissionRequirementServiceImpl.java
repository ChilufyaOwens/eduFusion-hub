package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.constants.RequirementType;
import com.owens.edu.programservice.controller.request.AdmissionRequirementRequest;
import com.owens.edu.programservice.dto.AdmissionRequirementResponse;
import com.owens.edu.programservice.dto.mapper.AdmissionRequirementMapper;
import com.owens.edu.programservice.dto.mapper.AdmissionRequirementResponseMapper;
import com.owens.edu.programservice.dto.mapper.RequiredCourseMapper;
import com.owens.edu.programservice.entity.AdmissionRequirement;
import com.owens.edu.programservice.entity.Program;
import com.owens.edu.programservice.entity.RequiredCourse;
import com.owens.edu.programservice.exception.ProgramNotFoundException;
import com.owens.edu.programservice.repository.AdmissionRequirementRepository;
import com.owens.edu.programservice.repository.ProgramRepository;
import com.owens.edu.programservice.service.AdmissionRequirementService;
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
    private final RequiredCourseMapper requiredCourseMapper;
    @Override
    public AdmissionRequirementResponse createProgramAdmissionRequirement(AdmissionRequirementRequest request) {
        //Check if program exists
        Program program = programRepository.findById(request.getProgramId()).orElseThrow(() -> new ProgramNotFoundException(String.
                format("Program with Id: %s not found", request.getProgramId())));

        AdmissionRequirement admissionRequirement = admissionRequirementMapper.toEntity(request);
        admissionRequirement.setProgram(program);

        AdmissionRequirement savedAdmissionRequirement = admissionRequirementRepository.save(admissionRequirement);

        return admissionRequirementResponseMapper.toDto(savedAdmissionRequirement);
    }

    @Override
    public List<AdmissionRequirementResponse> getProgramAdmissionRequirementsById(Long programId) {
        //Check if program exists
        Program program = programRepository.findById(programId).orElseThrow(() -> new ProgramNotFoundException(
                String.format("Program with Id: %s not found", programId)
        ));

        List<AdmissionRequirement> admissionRequirementsByProgram = admissionRequirementRepository.findAdmissionRequirementsByProgram(program);
        return buildAdmissionRequirementsResponse(admissionRequirementsByProgram);
    }

    private List<AdmissionRequirementResponse> buildAdmissionRequirementsResponse(
            List<AdmissionRequirement> admissionRequirementsByProgram) {

       return admissionRequirementsByProgram.stream()
                .map(admissionRequirementResponseMapper::toDto)
                .toList();
    }

    @Override
    public AdmissionRequirementResponse getAdmissionRequirementById(Long admissionRequirementsId) {
        //Check if admissionRequirement exists
        AdmissionRequirement admissionRequirement = admissionRequirementRepository.findById(admissionRequirementsId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("Admission requirement with Id: '%s' not found", admissionRequirementsId)
                ));

        return admissionRequirementResponseMapper.toDto(admissionRequirement);
    }

    @Override
    public List<AdmissionRequirementResponse> getAllAdmissionRequirement() {
        //Get all created admission requirements for every program
        List<AdmissionRequirement> admissionRequirements = admissionRequirementRepository.findAll();
        return buildAdmissionRequirementsResponse(admissionRequirements);
    }

    @Override
    public AdmissionRequirementResponse updateProgramAdmissionRequirement(
            Long admissionRequirementId,
            AdmissionRequirementRequest updateRequest) {

        //Check if admissionRequirement exists
        AdmissionRequirement admissionRequirement = admissionRequirementRepository.findById(admissionRequirementId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("AdmissionRequirement with Id: '%s' not found", admissionRequirementId)
                ));

        AdmissionRequirement updatedAdmissionRequirement = admissionRequirementRepository.save(
                updateAdmissionRequirement(admissionRequirement, updateRequest)
        );

        return admissionRequirementResponseMapper.toDto(updatedAdmissionRequirement);
    }

    private AdmissionRequirement updateAdmissionRequirement(
            AdmissionRequirement admissionRequirement,
            AdmissionRequirementRequest updateRequest) {

        admissionRequirement.setRequirementType(RequirementType.valueOf(updateRequest.getRequirementType()));
        admissionRequirement.setMinimumCredits(updateRequest.getMinimumCredits());
        admissionRequirement.setRequirementDescription(updateRequest.getRequirementDescription());

        Set<RequiredCourse> courses = updateRequest.getRequiredCourses().stream()
                .map(requiredCourseMapper::toEntity)
                .collect(Collectors.toSet());

        admissionRequirement.setRequiredCourses(courses);

        Optional<Program> optionalProgram = programRepository.findById(updateRequest.getProgramId());
        if(optionalProgram.isPresent() && (admissionRequirement.getProgram() != optionalProgram.get())){
                admissionRequirement.setProgram(optionalProgram.get());
        }

        return admissionRequirement;
    }

    @Override
    public String deleteAdmissionRequirementById(Long admissionRequirementId) {
        //Check if admissionRequirement exists
        AdmissionRequirement admissionRequirement = admissionRequirementRepository.findById(admissionRequirementId)
                .orElseThrow(() -> new ProgramNotFoundException(
                        String.format("AdmissionRequirement with Id: '%s' not found.", admissionRequirementId)
                ));

        //Delete admissionRequirement
        admissionRequirementRepository.delete(admissionRequirement);

        return String.format("AdmissionRequirement with Id: '%s' deleted.", admissionRequirementId);
    }
}
