package com.owens.edu.programservice.constants;

import lombok.Getter;

@Getter
public enum DegreeType {
    BACHELORS("Bachelor's"),
    MASTERS("Master's"),
    PHD("Ph.D");
    private final String displayName;

    DegreeType(String displayName) {
        this.displayName = displayName;
    }
}
