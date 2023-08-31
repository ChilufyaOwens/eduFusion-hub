package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.AdmissionRequirementRequest;
import com.owens.edu.programservice.dto.AdmissionRequirementResponse;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.service.AdmissionRequirementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admissions/requirement")
@RequiredArgsConstructor
public class AdmissionRequirementController {

    private final AdmissionRequirementService admissionRequirementService;

    @PostMapping(name = "CreateAdmissionRequirements")
    public ResponseEntity<ApiResponse> createAdmissionRequirement(
            @Valid @RequestBody AdmissionRequirementRequest request) {
        AdmissionRequirementResponse admissionRequirement = admissionRequirementService
                .createProgramAdmissionRequirement(request);

        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message("Program admission requirement, saved.")
                .data(admissionRequirement)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(value = "program/{id}", name = "GetProgramAdmissionRequirementsById")
    public ResponseEntity<ApiResponse> getProgramAdmissionRequirementsById(@PathVariable(name = "id") Long programId) {
        if (programId == null) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(false)
                    .message("ProgramId cannot be null")
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        List<AdmissionRequirementResponse> programAdmissionRequirementsById =
                admissionRequirementService.getProgramAdmissionRequirementsById(programId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Program admission requirement with Id: %s", programId))
                        .data(programAdmissionRequirementsById)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "{id}", name = "GetAdmissionRequirementById")
    public ResponseEntity<ApiResponse> getAdmissionRequirementById(@PathVariable(name = "id") Long admissionRequirementId){
        if(admissionRequirementId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("AdmissionRequirementId cannot be null")
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }
        //Get admission requirement
        AdmissionRequirementResponse admissionRequirementById = admissionRequirementService
                .getAdmissionRequirementById(admissionRequirementId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Admission requirement with with Id %s", admissionRequirementId))
                        .data(admissionRequirementById)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(name = "GetAlAdmissionRequirement")
    public ResponseEntity<ApiResponse> getAllAdmissionRequirement(){
        List<AdmissionRequirementResponse> allAdmissionRequirements = admissionRequirementService.getAllAdmissionRequirement();
        if(allAdmissionRequirements.isEmpty()){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(true)
                    .message("No admission requirements")
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("List of admission requirements")
                        .data(allAdmissionRequirements)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "{id}", name = "UpdateProgramAdmissionRequirement")
    public ResponseEntity<ApiResponse> updateProgramAdmissionRequirement(
            @PathVariable(name = "id") Long admissionRequirementId,
            @Valid @RequestBody AdmissionRequirementRequest request
    ){
        if(admissionRequirementId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(false)
                    .message("admissionRequirementId cannot be null")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        AdmissionRequirementResponse updatedAdmissionRequirementResponse = admissionRequirementService
                .updateProgramAdmissionRequirement(admissionRequirementId, request);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Admission requirement with Id: %s updated.", admissionRequirementId))
                        .data(updatedAdmissionRequirementResponse)
                        .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(value = "{id}", name = "DeleteAdmissionRequirementById")
    public ResponseEntity<ApiResponse> deleteAdmissionRequirementById(
            @PathVariable(name = "id") Long admissionRequirementId){
        if(admissionRequirementId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("admissionRequirementId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST

            );
        }
        String deleteAdmissionRequirementById = admissionRequirementService
                .deleteAdmissionRequirementById(admissionRequirementId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(deleteAdmissionRequirementById)
                        .data(deleteAdmissionRequirementById)
                        .build(), HttpStatus.OK
        );
    }

}
