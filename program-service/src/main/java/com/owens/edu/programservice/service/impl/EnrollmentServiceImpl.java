package com.owens.edu.programservice.service.impl;

import com.owens.edu.programservice.controller.request.EnrollmentRequest;
import com.owens.edu.programservice.repository.EnrollmentRepository;
import com.owens.edu.programservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.est.EnrollmentResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ENROLLMENT_SERVICE")
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    @Override
    public EnrollmentResponse enroll(EnrollmentRequest request) {
        log.info("Enrolling student with id: {} to the program ", request.getStudentId());


        return null;
    }
}
