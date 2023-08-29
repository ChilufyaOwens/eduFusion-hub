package com.owens.edu.programservice.dto.mapper;

import com.owens.edu.programservice.dto.LearningOutcomeDto;
import com.owens.edu.programservice.entity.LearningOutcome;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LearningOutcomeMapper {
    LearningOutcome toEntity(LearningOutcomeDto learningOutcomeDto);

    LearningOutcomeDto toDto(LearningOutcome learningOutcome);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LearningOutcome partialUpdate(LearningOutcomeDto learningOutcomeDto, @MappingTarget LearningOutcome learningOutcome);
}