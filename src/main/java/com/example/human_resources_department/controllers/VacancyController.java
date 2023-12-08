package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Vacancy;
import com.example.human_resources_department.services.VacancyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    public String showAllVacancies(
            Model model
    ) {
        List<Vacancy> allVacancies = vacancyService.getAllVacancies();

        model.addAttribute("allVacancies", allVacancies);

        return "allVacancies";
    }
}
