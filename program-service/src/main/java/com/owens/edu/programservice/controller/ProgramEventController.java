package com.owens.edu.programservice.controller;

import com.owens.edu.programservice.service.ProgramEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/program/events")
@RequiredArgsConstructor
public class ProgramEventController {

    private final ProgramEventService programEventService;
}
