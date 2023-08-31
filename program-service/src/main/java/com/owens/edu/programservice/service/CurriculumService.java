package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.CurriculumRequest;
import com.owens.edu.programservice.dto.CurriculumResponse;

import java.util.List;

public interface CurriculumService {
    CurriculumResponse createProgramCurriculum(CurriculumRequest request);
    CurriculumResponse getCurriculumByProgramId(Long programId);
    CurriculumResponse getCurriculumById(Long curriculumId);

    List<CurriculumResponse> getAllCurricula();
    CurriculumResponse updateCurriculum(Long curriculumId, CurriculumRequest updateRequest);
    String deleteCurriculumById(Long curriculumId);

}
