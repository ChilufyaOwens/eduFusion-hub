package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.CurriculumRequest;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.dto.CurriculumResponse;
import com.owens.edu.programservice.service.CurriculumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/curriculums")
@RequiredArgsConstructor
public class CurriculumController {

    private final CurriculumService curriculumService;

    @PostMapping(name = "CreateProgramCurriculum")
    public ResponseEntity<ApiResponse> createProgramCurriculum(@Valid @RequestBody CurriculumRequest request){

        CurriculumResponse programCurriculum = curriculumService.createProgramCurriculum(request);

        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message("Program curriculum created successfully")
                .data(programCurriculum)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", name = "GetCurriculumByProgramId")
    public ResponseEntity<ApiResponse> getCurriculumByProgramId(@PathVariable(name = "id") Long programId){

        if(programId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ProgramId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        CurriculumResponse curriculumByProgramId = curriculumService.getCurriculumByProgramId(programId);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Fetched program curriculum with programId: %s", programId))
                        .data(curriculumByProgramId)
                        .build(), HttpStatus.OK
        );
    }

    @GetMapping(value = "curriculum/{id}", name = "GetCurriculumById")
    public ResponseEntity<ApiResponse> getCurriculumById(@PathVariable(name = "id") Long curriculumId){

        if(curriculumId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("CurriculumId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        CurriculumResponse curriculumById = curriculumService.getCurriculumById(curriculumId);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Fetched curriculum with curriculumId: %s", curriculumId))
                        .data(curriculumById)
                        .build(), HttpStatus.OK
        );
    }

    @GetMapping(name = "GetAllCurricula" )
    public ResponseEntity<ApiResponse> getAllCurricula(){

        List<CurriculumResponse> allCurricula = curriculumService.getAllCurricula();

        if(allCurricula.isEmpty()){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(true)
                            .message("No curriculum created")
                            .build(), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Fetched programs curricula")
                        .data(allCurricula)
                        .build(), HttpStatus.OK
        );
    }

    @PutMapping(value = "{id}", name = "UpdateCurriculum")
    public ResponseEntity<ApiResponse> updateCurriculum(
            @PathVariable(name = "id") Long curriculumId,
            @Valid @RequestBody CurriculumRequest request){

        if(curriculumId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("CurriculumId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        CurriculumResponse curriculumResponse = curriculumService
                .updateCurriculum(curriculumId, request);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program curriculum updated")
                        .data(curriculumResponse)
                        .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(value = "{id}", name = "DeleteCurriculumById")
    public ResponseEntity<ApiResponse> deleteCurriculumById(@PathVariable(name = "id") Long curriculumId){
        if(curriculumId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("CurriculumId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        String deleteCurriculumById = curriculumService.deleteCurriculumById(curriculumId);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(deleteCurriculumById)
                        .build(), HttpStatus.OK
        );
    }
}
