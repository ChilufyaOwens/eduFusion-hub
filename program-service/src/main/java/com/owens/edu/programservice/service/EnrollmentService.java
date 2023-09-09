package com.owens.edu.programservice.service;

import com.owens.edu.programservice.controller.request.EnrollmentRequest;
import org.bouncycastle.est.EnrollmentResponse;

public interface EnrollmentService {
    EnrollmentResponse enroll(EnrollmentRequest request);

}
