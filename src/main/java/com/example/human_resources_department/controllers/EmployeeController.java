package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.repositories.EmployeeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
//@PreAuthorize("hasAuthority('HR_MANAGER')")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String employeeList(
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

        return "employeeList";
    }

    @GetMapping("{employee}")
    public String employeeEditForm(
            @PathVariable Employee employee,
            Model model
    ) {
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());

        return "employeeProfile";
    }

    @PostMapping
    public String employeeEditorSave(
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String lastName,
            @RequestParam Map<String, String> form,
            @RequestParam("employeeId") Employee employee
    ) {
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setLastName(lastName);

        //get all roles as String
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        employee.getRoles().clear();

        //for check and role's name in line
        for (String check : form.keySet()) {
            if (roles.contains(check)) {
                employee.getRoles().add(Role.valueOf(check));
            }
        }

        employeeRepository.save(employee);

        return "redirect:/employee";
    }

}
