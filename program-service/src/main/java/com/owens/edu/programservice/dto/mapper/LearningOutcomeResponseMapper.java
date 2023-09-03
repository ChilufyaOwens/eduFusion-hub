package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.LearningOutcomeResponse;
import com.owens.edu.programservice.entity.LearningOutcome;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LearningOutcomeResponseMapper {
    LearningOutcome toEntity(LearningOutcomeResponse learningOutcomeResponse);

    LearningOutcomeResponse toDto(LearningOutcome learningOutcome);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LearningOutcome partialUpdate(LearningOutcomeResponse learningOutcomeResponse, @MappingTarget LearningOutcome learningOutcome);
}