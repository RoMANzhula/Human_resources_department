package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/structure")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER')")
public class StructureController {
    private final UserService userService;

    public StructureController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showStructurePage() {
        return "structure";
    }

    @GetMapping("/position/{role}")
    public String showEmployeesByPosition(
            @PathVariable Role role,
            Model model
    ) {
        List<User> employees = userService.findUsersByRole(role);

        model.addAttribute("coworkers", employees);

        return "coworkersByPosition";
    }
}
