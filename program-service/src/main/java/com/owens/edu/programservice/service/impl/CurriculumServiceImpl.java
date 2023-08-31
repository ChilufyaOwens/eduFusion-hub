package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.CurriculumRequest;
import com.owens.edu.programservice.dto.CurriculumResponse;
import com.owens.edu.programservice.repository.CurriculumRepository;
import com.owens.edu.programservice.service.CurriculumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "CURRICULUM_SERVICE")
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    @Override
    public CurriculumResponse createProgramCurriculum(CurriculumRequest request) {
        return null;
    }

    @Override
    public CurriculumResponse getCurriculumByProgramId(Long programId) {
        return null;
    }

    @Override
    public CurriculumResponse getCurriculumById(Long curriculumId) {
        return null;
    }

    @Override
    public List<CurriculumResponse> getAllCurricula() {
        return null;
    }

    @Override
    public CurriculumResponse updateCurriculum(Long curriculumId, CurriculumRequest updateRequest) {
        return null;
    }

    @Override
    public String deleteCurriculumById(Long curriculumId) {
        return null;
    }
}
