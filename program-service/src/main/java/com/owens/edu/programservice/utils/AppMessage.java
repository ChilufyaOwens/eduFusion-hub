package com.owens.edu.programservice.utils;

public class AppMessage {

    // Define a constants for the error messages
    public static final String MODULE_NOT_FOUND_ERROR_MESSAGE ="Module with id: '%s' not found";
    public static final String CURRICULUM_NOT_FOUND_ERROR_MESSAGE = "Curriculum with id: '%s' not found";
    public static final String PROGRAM_NOT_FOUND_ERROR_MESSAGE = "Program with Id: '%s' not found";
    public static final String ADMISSION_REQUIREMENT_NOT_FOUND_ERROR_MESSAGE = "Admission requirement with Id: '%s' not found";
    public static final String LEARNING_OUTCOME_NOT_FOUND_ERROR_MESSAGE = "Learning outcome with Id: '%s' not found";
    public static final String LEARNING_OUTCOME_BY_PROGRAM_NOT_FOUND_ERROR_MESSAGE = "Learning outcome by program with id: '%s' not found";
    public static final String PROGRAM_EVENT_NOT_FOUND_ERROR_MESSAGE = "ProgramEvent with Id: '%s' not found";
    public static final String PROGRAM_EVENT_BY_PROGRAM_NOT_FOUND_ERROR_MESSAGE = "ProgramEvent with programId: '%s' not found";
    public static final String CURRICULUM_BY_PROGRAM_NOT_FOUND_ERROR_MESSAGE = "Curriculum for '%s' program not found";
    public static final String CURRICULUM_ALREADY_EXISTS_ERROR_MESSAGE = "Curriculum already exists for '%s' program";
    public static final String PROGRAM_ALREADY_EXISTS_ERROR_MESSAGE = "Program with name '%s' and degree type '%s' already exists";
    private AppMessage(){}
}
