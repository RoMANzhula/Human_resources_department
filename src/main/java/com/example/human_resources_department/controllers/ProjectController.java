package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.repositories.ProjectRepository;
import com.example.human_resources_department.services.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER') || hasAuthority('PROJECT_MANAGER')")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String showProjectsPage(
            Model model
    ) {
        List<Project> listOfProjects = projectRepository.findAll();

        if (!listOfProjects.isEmpty()) {
            model.addAttribute("projects", listOfProjects);
        } else {
            System.out.println("Projects not found!");
        }

        return "projects";
    }

    @GetMapping("/project_info/{project_id}")
    public String showProjectInfo(
            @PathVariable Long project_id,
            Model model
    ) {
        Project project = projectService.findProjectById(project_id);


        model.addAttribute("project", project);


        return "projectInfo";
    }

}
