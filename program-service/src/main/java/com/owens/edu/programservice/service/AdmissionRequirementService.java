package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.AdmissionRequirementRequest;
import com.owens.edu.programservice.dto.AdmissionRequirementResponse;

import java.util.List;

public interface AdmissionRequirementService {
    AdmissionRequirementResponse createProgramAdmissionRequirement(AdmissionRequirementRequest request);
    List<AdmissionRequirementResponse> getProgramAdmissionRequirementsById(Long programId);
    AdmissionRequirementResponse getAdmissionRequirementById(Long admissionRequirementId);
    List<AdmissionRequirementResponse> getAllAdmissionRequirement();
    AdmissionRequirementResponse updateProgramAdmissionRequirement(
            Long admissionRequirementId,
            AdmissionRequirementRequest updateRequest);
    String deleteAdmissionRequirementById(Long admissionRequirementId);

}
