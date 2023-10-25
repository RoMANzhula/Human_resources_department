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
            Model model
    ) {

        Iterable<Employee> listOfEmployees = employeeRepository.findAll();
        model.addAttribute("employees", listOfEmployees);

        return "main";
    }

    @PostMapping("/main")
    public String addEmployee(
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String lastName,
            @RequestParam String phone,
            @RequestParam String additionalPhone,
            Model model
    ) {
        Employee newEmployee = new Employee(
                firstName,
                secondName,
                lastName,
                phone,
                additionalPhone
        );

        employeeRepository.save(newEmployee);

        //list of all employees after added new
        Iterable<Employee> listOfEmployees = employeeRepository.findAll();

        model.addAttribute("employees", listOfEmployees);

        return "main";
    }

    @PostMapping("/filter")
    public String findByLastName(
            @RequestParam String lastName,
            Model model
    ) {
        Iterable<Employee> listOfEmployees;

        if (lastName != null && !lastName.isEmpty()) {
            listOfEmployees = employeeRepository.findByLastName(lastName);
        } else {
            listOfEmployees = employeeRepository.findAll();
        }

        model.addAttribute("employees", listOfEmployees);

        return "main";
    }

}
