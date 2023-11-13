package com.example.human_resources_department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/structure")
public class StructureController {

    @GetMapping
    public String showStructurePage() {
        return "structure";
    }
}
