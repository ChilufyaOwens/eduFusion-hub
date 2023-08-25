package com.owens.edu.studentservice.service.impl;

import com.owens.edu.studentservice.entity.StudentNumber;
import com.owens.edu.studentservice.respository.StudentNumberRepository;
import com.owens.edu.studentservice.service.StudentNumberGenerator;
import com.owens.edu.studentservice.util.StudentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "STUDENT_NUMBER_GENERATOR")
@RequiredArgsConstructor
public class StudentNumberGeneratorImpl implements StudentNumberGenerator {

    private final StudentNumberRepository studentNumberRepository;

    /**
     * method to check if a newly generated patient student number already exists in the database. We keep generating
     * new student  numbers until we find one that is unique.
     * @return student number
     */
    @Override
    public String generateStudentNumber() {

        log.info("Generating student number");
        String studentNumber;

        do{
            //Generate unique student number
            studentNumber = StudentUtil.generateStudentNumber();
        }while (studentNumberRepository.findStudentNumberByStudentNumber(studentNumber).isPresent());

        //Save student number
        StudentNumber saveStudentNumber = studentNumberRepository.save(
                StudentNumber.builder()
                        .studentNumber(studentNumber)
                        .build());
        log.info("Student number:  {} generated.", saveStudentNumber.getStudentNumber());
        return saveStudentNumber.getStudentNumber();
    }
}
