package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.event.ProgramEnrollmentEvent;
import com.owens.edu.programservice.service.EnrollmentOrchestrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ENROLLMENT_ORCHESTRATION_SERVICE")
public class EnrollmentOrchestrationServiceImpl implements EnrollmentOrchestrationService {


    @Override
    public void studentEnrollmentEventListener(ProgramEnrollmentEvent programEnrollmentEvent) {

        //This will be eventually implemented
    }
}
