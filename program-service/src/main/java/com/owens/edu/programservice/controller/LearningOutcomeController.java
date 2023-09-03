package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.LearningOutcomeRequest;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.dto.LearningOutcomeResponse;
import com.owens.edu.programservice.service.LearningOutcomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/program/learning-outcome")
@RequiredArgsConstructor
public class LearningOutcomeController {

    private final LearningOutcomeService learningOutcomeService;

    @PostMapping(name = "CreateLearningOutcome")
    public ResponseEntity<ApiResponse> createLearningOutcome(@Valid @RequestBody LearningOutcomeRequest request){

        LearningOutcomeResponse learningOutcome = learningOutcomeService.createLearningOutcome(request);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program's learning outcome created successfully.")
                        .data(learningOutcome)
                        .build(), HttpStatus.CREATED
        );
    }

    @GetMapping(value = "{id}", name = "GetLearningOutcomeByProgramId")
    public ResponseEntity<ApiResponse> getLearningOutcomeByProgramId(@PathVariable(name = "id") Long programId){

        if(programId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ProgramId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }
        LearningOutcomeResponse learningOutcomeByProgramId = learningOutcomeService
                .getLearningOutcomeByProgramId(programId);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program's learning outcome, fetched")
                        .data(learningOutcomeByProgramId)
                        .build(), HttpStatus.OK
        );
    }

    @GetMapping(value = "learning/{id}", name = "GetLearningOutcomeById")
    public ResponseEntity<ApiResponse> getLearningOutcomeById(@PathVariable(name = "id") Long learningOutcomeId){
        if(learningOutcomeId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("LearningOutcomeId cannot be null.")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        LearningOutcomeResponse learningOutcomeById = learningOutcomeService.getLearningOutcomeById(learningOutcomeId);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Program's learning outcome with name: '%s' fetched",
                                        learningOutcomeById.getName()))
                        .data(learningOutcomeById)
                        .build(), HttpStatus.OK
        );
    }

    @GetMapping(name = "GetAllLearningOutcomes")
    public ResponseEntity<ApiResponse> getAllLearningOutcomes(){
        Set<LearningOutcomeResponse> allLearningOutcome = learningOutcomeService.getAllLearningOutcome();
        if(allLearningOutcome.isEmpty()){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(true)
                            .message("No programs learning outcome found")
                            .build(), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Programs learning outcomes fetched.")
                        .data(allLearningOutcome)
                        .build(), HttpStatus.OK
        );
    }

    @PutMapping(value = "{id}", name = "UpdateLearningOutcome")
    public ResponseEntity<ApiResponse> updateLearningOutcome(@PathVariable(name = "id") Long learningOutcomeId,
                                                             @Valid @RequestBody LearningOutcomeRequest request){
        //Check if learningOutcomeId is null
        if(learningOutcomeId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("Program's learningOutcomeId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        LearningOutcomeResponse learningOutcomeResponse = learningOutcomeService
                .updateLearningOutcome(learningOutcomeId, request);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program's learning outcome updated successfully.")
                        .data(learningOutcomeResponse)
                        .build(), HttpStatus.OK
        );

    }

    @DeleteMapping(value = "{id}", name = "DeleteLearningOutcome")
    public ResponseEntity<ApiResponse> deleteLearningOutcome(@PathVariable(name = "id") Long learningOutcomeId){

        //Check if learningOut is not null
        if(learningOutcomeId ==  null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("Program's learningOutcomeId cannot be null.")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        String deleteLearningOutcomeById = learningOutcomeService.deleteLearningOutcomeById(learningOutcomeId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program's learning outcome deleted successfully.")
                        .data(deleteLearningOutcomeById)
                        .build(), HttpStatus.OK
        );
    }
}
