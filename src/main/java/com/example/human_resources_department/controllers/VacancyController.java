package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.models.Vacancy;
import com.example.human_resources_department.services.ProjectService;
import com.example.human_resources_department.services.VacancyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;
    private final ProjectService projectService;

    public VacancyController(
            VacancyService vacancyService,
            ProjectService projectService
    ) {
        this.vacancyService = vacancyService;
        this.projectService = projectService;
    }

    @GetMapping
    public String showAllVacancies(
            Model model
    ) {
        List<Vacancy> allVacancies = vacancyService.getAllVacancies();

        model.addAttribute("allVacancies", allVacancies);

        return "allVacancies";
    }

    @GetMapping("/{vacancyId}")
    public String showVacancyInfo(
            @PathVariable Long vacancyId,
            Model model
    ) {
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        model.addAttribute("vacancy", vacancy);

        return "vacancyInfoPage";
    }

    @GetMapping("/create")
    public String showCreateVacancyForm(
            Model model
    ) {
        List<Project> allProjects = projectService.getAllProjects();

        model.addAttribute("allProjects", allProjects);
        model.addAttribute("vacancy", new Vacancy());

        return "createVacancy";
    }

    @PostMapping("/create")
    public String createVacancy(
            @AuthenticationPrincipal User user,
            @RequestParam("selectedProject") Long selectedProjectId,
            @ModelAttribute Vacancy vacancy
    ) {
        try {
            vacancyService.createVacancy(user, vacancy, selectedProjectId);
            return "redirect:/vacancies";
        } catch (IllegalArgumentException e) {
            System.out.println("Start job must be in the future.");
            return "redirect:/vacancies/create";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditVacancyForm(
            @PathVariable Long id,
            Model model
    ) {
        Vacancy vacancy = vacancyService.getVacancyById(id);

        List<Project> allProjects = projectService.getAllProjects();

        model.addAttribute("allProjects", allProjects);
        model.addAttribute("vacancy", vacancy);

        return "editVacancy";
    }

    @PostMapping("/{id}/edit")
    public String editVacancy(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long id,
            @RequestParam(name = "selectedProject", required = false) Long selectedProjectId,
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @ModelAttribute Vacancy updatedVacancy
    ) {
        vacancyService.editVacancy(currentUser, id, selectedProjectId, isActive, updatedVacancy);

        return "redirect:/vacancies";
    }

}
