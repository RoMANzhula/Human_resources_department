package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    private EmployeeRepository employeeRepository;
    public GreetingController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String lastNameFilter,
            Model model
    ) {
        Iterable<Employee> listOfEmployees;

        if (lastNameFilter != null && !lastNameFilter.isEmpty()) {
            listOfEmployees = employeeRepository.findByLastName(lastNameFilter);
        } else {
            listOfEmployees = employeeRepository.findAll();
        }

        model.addAttribute("employees", listOfEmployees);
        model.addAttribute("lastNameFilter", lastNameFilter);

        return "main";
    }

    @PostMapping("/main")
    public String addEmployee(
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String lastNameFilter,
            @RequestParam String phone,
            @RequestParam String additionalPhone,
            Model model
    ) {
        Employee newEmployee = new Employee(
                firstName,
                secondName,
                lastNameFilter,
                phone,
                additionalPhone
        );

        employeeRepository.save(newEmployee);

        //list of all employees after added new
        Iterable<Employee> listOfEmployees = employeeRepository.findAll();

        model.addAttribute("employees", listOfEmployees);

        return "main";
    }

}
