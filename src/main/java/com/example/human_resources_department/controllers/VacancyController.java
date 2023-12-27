package com.example.human_resources_department.controllers;

import com.example.human_resources_department.dto.VacancyDTO;
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
import java.util.stream.Collectors;

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
        List<Vacancy> allVacancies = vacancyService.getAllVacancies()
                .stream()
                .map(VacancyDTO::toVacancy)
                .collect(Collectors.toList());

        model.addAttribute("allVacancies", allVacancies);

        return "allVacancies";
    }

    @GetMapping("/{vacancyId}")
    public String showVacancyInfo(
            @PathVariable Long vacancyId,
            Model model
    ) {
        Vacancy vacancy = vacancyService.getVacancyDTOById(vacancyId).toVacancy();
        VacancyDTO vacancyDTO = VacancyDTO.fromVacancy(vacancy);

        model.addAttribute("vacancy", vacancyDTO);

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
            @ModelAttribute VacancyDTO vacancyDTO
    ) {
        try {
            Vacancy vacancy = vacancyDTO.toVacancy();
            vacancyService.createVacancy(user, VacancyDTO.fromVacancy(vacancy), selectedProjectId);
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
        Vacancy vacancy = vacancyService.getVacancyDTOById(id).toVacancy();

        List<Project> allProjects = projectService.getAllProjects();

        model.addAttribute("allProjects", allProjects);
        model.addAttribute("vacancy", VacancyDTO.fromVacancy(vacancy));

        return "editVacancy";
    }

    @PostMapping("/{id}/edit")
    public String editVacancy(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long id,
            @RequestParam(name = "selectedProject", required = false) Long selectedProjectId,
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @ModelAttribute VacancyDTO updatedVacancyDTO
    ) {
        Vacancy updatedVacancy = updatedVacancyDTO.toVacancy();
        vacancyService.editVacancy(currentUser, id, selectedProjectId, isActive, VacancyDTO.fromVacancy(updatedVacancy));

        return "redirect:/vacancies";
    }

}
