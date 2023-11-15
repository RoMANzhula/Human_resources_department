package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProfessionController {
    private final EmployeeRepository employeeRepository;

    public ProfessionController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/professions")
    public String professionsPage(Model model) {
        List<Employee> employees = employeeRepository.findAll();

        // Підрахунок кількості працівників для кожної ролі
        Map<String, Integer> professionCounts = new HashMap<>();
        for (Employee employee : employees) {
            for (Role role : employee.getEmployeeRoles()) {
                String roleName = role.name();
                professionCounts.put(roleName, professionCounts.getOrDefault(roleName, 0) + 1);
            }
        }

        model.addAttribute("professionCounts", professionCounts);
        return "professions"; // Шлях до HTML-сторінки
    }
}
