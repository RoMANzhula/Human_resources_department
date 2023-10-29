package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employeeList(
            @RequestParam(required = false, defaultValue = "") String lastNameFilter,
            Model model
    ) {
        Iterable<Employee> listOfEmployees = employeeService.getEmployees(lastNameFilter);

        model.addAttribute("employees", listOfEmployees);
        model.addAttribute("lastNameFilter", lastNameFilter);

        return "employeeList";
    }

    @GetMapping("{employeeId}")
    public String employeeEditForm(
            @PathVariable("employeeId") Long employeeId,
            Model model
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());

        return "employeeProfile";
    }

    @PostMapping
    public String employeeEditorSave(
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam Map<String, String> form,
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("filePhoto") MultipartFile filePhoto
    ) throws IOException {
        Employee employee = employeeService.getEmployeeById(employeeId);

        employeeService.updateEmployee(employee, isActive, firstName, secondName, lastName, form, filePhoto);

        return "redirect:/employee";
    }
}