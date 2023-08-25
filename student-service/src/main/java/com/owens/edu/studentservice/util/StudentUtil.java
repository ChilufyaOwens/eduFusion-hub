package com.owens.edu.studentservice.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;


public class StudentUtil {

    private static final String STUDENT_NUMBER_PREFIX = "19871";
    private static final int STUDENT_NUMBER_LENGTH = 13;
    private static final String NUMERIC_STRING = "0123456789";
    private static final Random RANDOM = new SecureRandom();

    private StudentUtil(){}

    /**
     * generateStudentNumber method generates a unique student number containing only numbers with
     * a prefix code 19871 and current year contained. We add the prefix "19871"
     * to the student number, followed by the current year obtained using
     * the LocalDate.now().getYear() method. We then generate the remaining digits
     * randomly using the NUMERIC_STRING constant.
     * @return generated student number
     */
    public static String generateStudentNumber(){
        StringBuilder builder = new StringBuilder();

        // Add prefix
        builder.append(STUDENT_NUMBER_PREFIX);
        // Add current year
        int year = LocalDate.now().getYear();
        builder.append(year);

        // Add random digits
        int remainingDigits = STUDENT_NUMBER_LENGTH - STUDENT_NUMBER_PREFIX.length() - String.valueOf(year).length();
        for (int i = 0; i < remainingDigits; i++) {
            int index = RANDOM.nextInt(NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(index));
        }

        return builder.toString();
    }
}
