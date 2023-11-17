package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.services.EmployeeService;
import com.example.human_resources_department.services.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Value("${hostname}")
    private String hostname;
    private final MailSenderService mailSenderService;
    private final EmployeeService employeeService;

    public EmployeeController(
            MailSenderService mailSenderService, EmployeeService employeeService
    ) {
        this.mailSenderService = mailSenderService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String filterEmployeeList(
            @RequestParam(required = false, defaultValue = "") String lastNameFilter,
            Model model
    ) {
        Iterable<Employee> listOfEmployeesByFilter = employeeService.getEmployees(lastNameFilter);

        model.addAttribute("employees", listOfEmployeesByFilter);
        model.addAttribute("lastNameFilter", lastNameFilter);

        return "employeeList";
    }

    @GetMapping("/{employeeId}")
    public String employeeProfileEditor(
            @PathVariable("employeeId") Employee employeeId,
            Model model
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId.getId());
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());

        return "employeeProfile";
    }

    @PostMapping
    public String employeeProfileEditorSave(
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam Map<String, String> form,
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("filePhoto") MultipartFile filePhoto
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        employeeService.updateEmployee(employee, isActive, firstName, secondName, lastName, form, filePhoto);

        return "redirect:/employee";
    }

    @PostMapping("/sendActivationCode")
    public String sendActivationCodeEmail(
            @RequestParam("employeeId") Long employeeId
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee != null) {
            String activationCode = employee.getSecretCodeForRole();

            if (activationCode != null && !employee.getEmployeeRoles().isEmpty()) {
                String emailSubject = "Activation Code";
                String emailText = "Your activation code is: " + activationCode +
                        ". Visit this site for registration: " + hostname;
                mailSenderService.sendByMail(employee.getEmail(), emailSubject, emailText);

                System.out.println("Activation code email sent successfully.");
            } else {
                System.out.println("Dear HR, please check the role for employee.");
            }
        }

        return "redirect:/employee";
    }

}