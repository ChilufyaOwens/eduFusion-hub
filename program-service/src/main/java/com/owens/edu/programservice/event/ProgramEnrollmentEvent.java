package com.owens.edu.programservice.event;

import com.owens.edu.programservice.controller.request.EnrollmentRequest;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProgramEnrollmentEvent extends ApplicationEvent {

    private final EnrollmentRequest request;

    public ProgramEnrollmentEvent(Object source, EnrollmentRequest request) {
        super(source);
        this.request = request;
    }
}
