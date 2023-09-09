package com.owens.edu.programservice.service;

import com.owens.edu.programservice.event.ProgramEnrollmentEvent;

public interface EnrollmentOrchestrationService {
    void studentEnrollmentEventListener(ProgramEnrollmentEvent programEnrollmentEvent);
}
