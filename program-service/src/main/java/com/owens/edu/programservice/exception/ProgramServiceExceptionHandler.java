package com.owens.edu.programservice.exception;

import com.owens.edu.programservice.constants.ProgramApiError;
import com.owens.edu.programservice.dto.ProgramErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ProgramServiceExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * This method handles Program resource not found exception
     * @param exception not found exception
     * @param request web request
     * @return exception not found response entity
     */
    @ExceptionHandler(ProgramNotFoundException.class)
    public ResponseEntity<ProgramErrorDetails> handleProgramNotFoundException(
            ProgramNotFoundException exception,
            WebRequest request){

        ProgramErrorDetails errorDetails = ProgramErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(ProgramApiError.NOT_FOUND)
                .message(exception.getMessage())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles patient identification already exists exception
     * @param exception patient id already exists exception
     * @param request request
     * @return patient identification already exists exception
     */
    public ResponseEntity<ProgramErrorDetails> handleProgramAlreadyExists(
            ProgramAlreadyExistException exception,
            WebRequest request
    ){
        ProgramErrorDetails errorDetails = ProgramErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(request.getDescription(false))
                .errorCode(ProgramApiError.EMAIL_ALREADY_EXISTS)
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /**
     * This method handle global or generic exceptions
     * @param exception generic exception
     * @param request web request
     * @return generic exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProgramErrorDetails> handleGlobalException(Exception exception, WebRequest request){
        ProgramErrorDetails errorDetails = ProgramErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(request.getDescription(false))
                .errorCode(ProgramApiError.GENERAL_ERROR)
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles method args validation exceptions
     * @param ex exception
     * @param headers header
     * @param status status
     * @param request request
     * @return validation exception
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errorMap = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach(error-> {
            String fieldName =((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorMap.put(fieldName, message);

        });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
