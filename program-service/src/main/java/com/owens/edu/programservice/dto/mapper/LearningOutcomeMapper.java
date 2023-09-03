package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.controller.request.LearningOutcomeRequest;
import com.owens.edu.programservice.entity.LearningOutcome;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LearningOutcomeMapper {
    LearningOutcome toEntity(LearningOutcomeRequest learningOutcomeRequest);

    LearningOutcomeRequest toDto(LearningOutcome learningOutcome);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LearningOutcome partialUpdate(LearningOutcomeRequest learningOutcomeRequest, @MappingTarget LearningOutcome learningOutcome);
}