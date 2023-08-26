package com.owens.edu.commons.constants;

public enum ApiError {

    // Common errors
    GENERAL_ERROR("50000", "An internal server error occurred."),
    UNAUTHORIZED("40100", "Authentication failed."),
    FORBIDDEN("40300", "Permission denied."),
    NOT_FOUND("40400", "Resource not found."),

    // Custom errors
    MISSING_PARAMETER("40001", "Missing required parameter: %s."),
    INVALID_EMAIL("40002", "Invalid email format."),
    USER_NOT_FOUND("40401", "User with ID %s not found."),
    EMAIL_ALREADY_EXISTS("40902", "Email %s already exists.");


    private final String code;
    private final String message;

    ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }


}
