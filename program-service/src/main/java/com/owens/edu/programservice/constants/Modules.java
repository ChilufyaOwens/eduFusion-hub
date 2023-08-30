package com.owens.edu.programservice.constants;

import lombok.Getter;

@Getter
public enum Modules {
    MODULE_1("Module");

    private final String displayName;


    Modules(String displayName) {
        this.displayName = displayName;
    }
}
