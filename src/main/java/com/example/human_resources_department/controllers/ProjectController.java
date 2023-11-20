package com.example.human_resources_department.controllers;

import com.example.human_resources_department.dto.TeamCreationDto;
import com.example.human_resources_department.models.Project;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.ProjectRepository;
import com.example.human_resources_department.services.ProjectService;
import com.example.human_resources_department.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER') || hasAuthority('PROJECT_MANAGER')")
public class ProjectController {
    @Value("${hostname}")
    private String path;

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository,
                             UserService userService
    ) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.userService = userService;
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
        model.addAttribute("path", path);

        return "projectInfo";
    }

    @GetMapping("/create")
    public String showCreateProjectForm(
            Model model
    ) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "createProject";
    }

    @PostMapping("/create")
    public String createProject(
            @RequestParam("file") MultipartFile fileContractName,
            @ModelAttribute("project") Project project
    ) {
        if (project != null) {
            projectService.saveProject(project, fileContractName);
        }

        return "redirect:/project";
    }

    @GetMapping("/{projectId}/create-team")
    public String showCreateTeamPage(
            @PathVariable Long projectId,
            Model model
    ) {
        Project project = projectService.getProjectById(projectId);
        Map<Role, Integer> availableRoles = projectService.getAllRoles();

        model.addAttribute("project", project);
        model.addAttribute("availableRoles", availableRoles);

        return "createTeamPage";
    }

    @PostMapping("/{projectId}/create-team")
    public String createTeam(
            @PathVariable Long projectId,
            @ModelAttribute TeamCreationDto teamDTO
    ) {
        projectService.createTeam(projectId, teamDTO.getRolesAndCounts());

        return "redirect:/project/project_info/{projectId}";
    }

    @GetMapping("/{projectId}/show-team")
    public String showTeamPage(
            @PathVariable Long projectId,
            Model model
    ) {
        Project project = projectService.getProjectById(projectId);

        Map<String, List<User>> teamMembersByRole = projectService.findTeamMembersByRoleAndProject(projectId);

        model.addAttribute("teamMembersByRole", teamMembersByRole);
        model.addAttribute("project", project);

        return "showTeamPage";
    }

    @GetMapping("/{projectId}/choose-coworkers")
    public String showChooseCoworkersPage(
            @PathVariable Long projectId,
            @RequestParam Integer count,
            @RequestParam(required = false) String role,
            Model model
    ) {
        Project project = projectService.getProjectById(projectId);

        if (role != null) {
            List<User> usersByRole = userService.findUsersByRole(Role.valueOf(role));
            model.addAttribute("availableUsers", usersByRole);
        } else {
            System.out.println("Role in project table is not found!");
        }

        model.addAttribute("project", project);
        model.addAttribute("count", count);

        return "chooseCoworkersPage";
    }

    @PostMapping("/{projectId}/choose-coworkers")
    public String chooseCoworkers(
            @PathVariable Long projectId,
            @RequestParam Integer count,
            @RequestParam(name = "selectedUserIds", required = false) List<Long> selectedUserIds
    ) {
        if (selectedUserIds != null) {
            projectService.addSelectedUsersToProject(projectId, count, selectedUserIds);
        }

        return "redirect:/project/project_info/{projectId}";
    }

}
