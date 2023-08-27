package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.CreateProgramRequest;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.dto.CreateProgramResponse;
import com.owens.edu.programservice.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProgram(@Valid @RequestBody CreateProgramRequest request){
        CreateProgramResponse program = programService.createProgram(request);
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccess(true)
                .message("")
                .data(program)
                .build(), HttpStatus.CREATED);
    }
}
