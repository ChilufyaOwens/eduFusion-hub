package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.ProgramEventRequest;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.dto.ProgramEventResponse;
import com.owens.edu.programservice.service.ProgramEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/program/events")
@RequiredArgsConstructor
public class ProgramEventController {

    private final ProgramEventService programEventService;

    @PostMapping( name = "CreateProgramEvent")
    public ResponseEntity<ApiResponse> createProgramEvent(@Valid @RequestBody ProgramEventRequest request){

        ProgramEventResponse programEvent = programEventService.createProgramEvent(request);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program event created successfully")
                        .data(programEvent)
                        .build(), HttpStatus.CREATED
        );
    }

    @GetMapping(value = "program/{id}", name = "GetProgramEventByProgramId")
    public ResponseEntity<ApiResponse> getProgramEventByProgramId(@PathVariable(name = "id") Long programId){

        //Check if programId is null
        if(programId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ProgramId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        ProgramEventResponse programEventByProgramId = programEventService.getProgramEventByProgramId(programId);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program event fetched successfully.")
                        .data(programEventByProgramId)
                        .build(), HttpStatus.OK
        );

    }

    @GetMapping(value = "{id}", name = "GtProgramEventById")
    public ResponseEntity<ApiResponse> getProgramEventById(@PathVariable(name = "id") Long eventId){
        //Check if eventId is not null
        if(eventId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ProgramEventId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        ProgramEventResponse programEventById = programEventService.getProgramEventById(eventId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program event fetched successfully.")
                        .data(programEventById)
                        .build(), HttpStatus.OK
        );
    }

    @GetMapping(name = "GetAllEvents")
    public ResponseEntity<ApiResponse> getAllEvents(){
        List<ProgramEventResponse> allEvents = programEventService.getAllEvents();

        //Check if there's an event
        if(allEvents.isEmpty()){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(true)
                            .message("There are no program events running currently")
                            .build(), HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Fetched all program events")
                        .data(allEvents)
                        .build(), HttpStatus.OK
        );
    }

    @PutMapping(value = "{id}", name = "UpdateProgramEventById")
    public ResponseEntity<ApiResponse> updateProgramEventById(@PathVariable(name = "id") Long eventId,
                                                              @Valid @RequestBody ProgramEventRequest request){
        //Check if eventId is not null
        if(eventId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("EventId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        ProgramEventResponse programEventResponse = programEventService.updateProgramEventById(eventId, request);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program event updated successfully.")
                        .data(programEventResponse)
                        .build(), HttpStatus.OK
        );
    }

    @PutMapping(value = "cancel/{id}", name = "CancelProgramEventById")
    public ResponseEntity<ApiResponse> cancelProgramEventById(@PathVariable(name = "id") Long eventId){
        //Check if eventId is null
        if ((eventId == null)){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("EventId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        ProgramEventResponse programEventResponse = programEventService.cancelProgramEventById(eventId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program event cancelled successfully.")
                        .data(programEventResponse)
                        .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(value = "{id}",name = "DeleteProgramEventById")
    public ResponseEntity<ApiResponse> deleteProgramEventById(@PathVariable(name = "id") Long eventId){
        //Check if eventId is null
        if ((eventId == null)){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("EventId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        String deleteProgramEventById = programEventService.deleteProgramEventById(eventId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Program event deleted successfully.")
                        .data(deleteProgramEventById)
                        .build(), HttpStatus.OK
        );
    }
}
