package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.EmployeeService;
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
public class StructureController {
    private final UserService userService;
    private final EmployeeService employeeService;

    public StructureController(
            UserService userService,
            EmployeeService employeeService
    ) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showStructurePage() {
        return "structure";
    }

    @GetMapping("/position/{role}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('HR_MANAGER')")
    public String showEmployeesByPosition(
            @PathVariable Role role,
            Model model
    ) {
        List<User> coworkers = userService.findUsersByRole(role);

        for (User coworker : coworkers) {
            Employee employee = employeeService.findEmployeeBySecretCodeForRole(coworker);
            model.addAttribute("employee", employee);
        }

        model.addAttribute("coworkers", coworkers);

        return "coworkersByPosition";
    }
}
