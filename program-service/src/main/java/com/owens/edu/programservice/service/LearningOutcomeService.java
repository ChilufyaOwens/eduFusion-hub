package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.LearningOutcomeRequest;
import com.owens.edu.programservice.dto.LearningOutcomeResponse;

import java.util.List;
import java.util.Set;

public interface LearningOutcomeService {
    LearningOutcomeResponse createLearningOutcome(LearningOutcomeRequest request);

    LearningOutcomeResponse getLearningOutcomeByProgramId(Long programId);

    LearningOutcomeResponse getLearningOutcomeById(Long learningOutcomeId);

    Set<LearningOutcomeResponse> getAllLearningOutcome();
    LearningOutcomeResponse updateLearningOutcome(Long learningOutcomeId, LearningOutcomeRequest request);

    String deleteLearningOutcomeById(Long learningOutcomeId);
}
