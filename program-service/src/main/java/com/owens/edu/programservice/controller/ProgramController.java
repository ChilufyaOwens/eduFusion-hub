package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.controller.request.UpdateProgramRequest;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.dto.ProgramResponse;
import com.owens.edu.programservice.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    /**
     * createProgram method creates a new education program that students will enroll in
     * @param request request to create a program
     * @return created program response
     */
    @PostMapping(name = "CreateProgram")
    public ResponseEntity<ApiResponse> createProgram(@Valid @RequestBody CreateProgramRequest request){
        ProgramResponse program = programService.createProgram(request);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message("Program created successfully.")
                .data(program)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", name = "GetProgramById")
    public ResponseEntity<ApiResponse> getProgramById(@PathVariable(name = "id") Long programId){
        if(programId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(false)
                    .message("ProgramId cannot be null or empty")
                    .build(), HttpStatus.BAD_REQUEST);
        }

        ProgramResponse programById = programService.getProgramById(programId);

        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message(String.format("Program with programId: %s.", programId))
                .data(programById)
                .build(), HttpStatus.OK);
    }

    @GetMapping(name = "GetPrograms")
    public ResponseEntity<ApiResponse> getPrograms(){

        List<ProgramResponse> programs = programService.getPrograms();
        if (programs.isEmpty()){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(true)
                    .message("No programs created")
                    .data(null)
                    .build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message("All created programs")
                .data(programs)
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", name = "UpdateProgramData")
    public ResponseEntity<ApiResponse> updateProgramData(@PathVariable(name = "id") Long programId,
                                                            @Valid @RequestBody UpdateProgramRequest request){
        if(programId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(true)
                    .message("programId cannot be null")
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        ProgramResponse programResponse = programService.updateProgram(programId, request);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message("Program data updated successfully")
                .data(programResponse)
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = "discontinue/{id}", name = "DiscontinueProgram")
    public ResponseEntity<ApiResponse> discontinueProgram(@PathVariable(name = "id") Long programId){
        if(programId == null){
            return new ResponseEntity<>(ApiResponse.builder()
                    .isSuccess(false)
                    .message("ProgramId cannot be null or empty")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        String response = programService.discontinueProgram(programId);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message(response)
                .data(true)
                .build(), HttpStatus.OK);

    }

    @DeleteMapping(value = "{id}", name = "DeleteProgramById")
    public ResponseEntity<ApiResponse> deleteProgramById(@PathVariable(name = "id") Long programId){
        if(programId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ProgramId cannot be null or empty")
                            .data(null)
                            .build(), HttpStatus.BAD_REQUEST
            );
        }
        String deleteProgramById = programService.deleteProgramById(programId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(deleteProgramById)
                        .data(true)
                        .build(), HttpStatus.OK
        );
    }
}
