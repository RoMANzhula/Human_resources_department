package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.EmployeeRepository;
import com.example.human_resources_department.services.EmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    public MainController(
            EmployeeService employeeService,
            EmployeeRepository employeeRepository
    ) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String mainPage(
            @RequestParam(required = false, defaultValue = "") String lastNameFilter,
            Model model
    ) {
        Iterable<Employee> listOfEmployees = employeeService.getFilteredEmployees(lastNameFilter);
        model.addAttribute("employees", listOfEmployees);
        model.addAttribute("lastNameFilter", lastNameFilter);

        return "main";
    }

    @PostMapping("/main")
    public String addEmployeeByHR(
            @AuthenticationPrincipal User recruiter,
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String lastName,
            @RequestParam String phone,
            @RequestParam String email,
            Model model
    ) {

        Employee existingEmployee = employeeRepository.findByPhone(phone);
        if (existingEmployee != null) {
            model.addAttribute("message", "Employee with this phone already exists.");
        } else {
            if (employeeService.addEmployee(recruiter, firstName, secondName, lastName, phone, email)) {
                //employee was added successfully
                Iterable<Employee> listOfEmployees = employeeRepository.findAll();
                model.addAttribute("employees", listOfEmployees);
            }
        }

        return "main";
    }

}
