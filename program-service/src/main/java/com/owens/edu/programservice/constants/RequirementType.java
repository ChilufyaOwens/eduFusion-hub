package com.owens.edu.programservice.constants;

import lombok.Getter;

@Getter
public enum RequirementType {
    PREREQUISITE("Prerequisite"),
    ELECTIVE("Elective");
    private final String displayName;

    RequirementType(String displayName) {
        this.displayName = displayName;
    }
}
