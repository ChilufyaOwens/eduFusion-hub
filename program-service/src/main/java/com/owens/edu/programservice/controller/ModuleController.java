package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.controller.request.CourseRequest;
import com.owens.edu.programservice.controller.request.ModuleRequest;
import com.owens.edu.programservice.dto.ApiResponse;
import com.owens.edu.programservice.dto.CourseResponse;
import com.owens.edu.programservice.dto.ModuleResponse;
import com.owens.edu.programservice.service.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping(name = "AddModule")
    public ResponseEntity<ApiResponse> addModule(@Valid @RequestBody ModuleRequest request){

        ModuleResponse moduleResponse = moduleService.addCurriculumModule(request);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(String.format("Successfully added curriculum module with name: '%s'", request.getName()))
                        .data(moduleResponse)
                        .build(), HttpStatus.CREATED
        );

    }

    @PostMapping(name = "AddModuleCourses")
    public ResponseEntity<ApiResponse> addModuleCourses(@Valid @RequestBody CourseRequest request){

        Set<CourseResponse> courseResponseList = moduleService.addModuleCourses(request);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Successfully added courses to the module")
                        .data(courseResponseList)
                        .build(), HttpStatus.CREATED
        );
    }

    @GetMapping(value = "{id}", name = "GetModuleById")
    public ResponseEntity<ApiResponse> getModuleById(@PathVariable(name = "id") Long moduleId){
        //Check moduleId is null
        if(moduleId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ModuleId cannot not be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }
       ModuleResponse moduleResponse = moduleService.fetchCurriculumModuleById(moduleId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message("Module details fetched successfully")
                        .data(moduleResponse)
                        .build(), HttpStatus.OK
        );

    }
    @DeleteMapping(value = "{id}", name = "RemoveModuleById")
    public ResponseEntity<ApiResponse> removeModuleById(@PathVariable(name = "id") Long moduleId){
        //Check if moduleId is null
        if(moduleId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ModuleId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        String removeModuleById = moduleService.deleteCurriculumModuleById(moduleId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(removeModuleById)
                        .data(removeModuleById)
                        .build(), HttpStatus.OK
        );

    }

    @DeleteMapping(value = "/courses/{id}", name = "RemoveModuleCourseById")
    public ResponseEntity<ApiResponse> removeModuleCourseById(@PathVariable(name = "id") Long courseId){

        //Check if courseId is null
        if(courseId == null){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .isSuccess(false)
                            .message("ModuleId cannot be null")
                            .build(), HttpStatus.BAD_REQUEST
            );
        }

        String removeCourseById = moduleService.removeCourseById(courseId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .isSuccess(true)
                        .message(removeCourseById)
                        .data(removeCourseById)
                        .build(), HttpStatus.OK
        );
    }
}
