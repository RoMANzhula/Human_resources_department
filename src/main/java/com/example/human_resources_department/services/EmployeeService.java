package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean addEmployee(
            User recruiter, String firstName,
            String secondName, String lastName,
            String phone, String email) {
        Employee newEmployee = new Employee(
                firstName, secondName,
                lastName, phone,
                email, recruiter
        );

        Employee employeeFromDB = employeeRepository.findByPhone(phone);

        if (employeeFromDB != null) {
            return false;
        }

        newEmployee.setActive(true);
        newEmployee.setDateOfRegistration(new Date());
        newEmployee.generateSecretCode();
        employeeRepository.save(newEmployee);

        return true;
    }

    public Iterable<Employee> getFilteredEmployees(String lastNameFilter) {
        if (lastNameFilter != null && !lastNameFilter.isEmpty()) {
            return employeeRepository.findByLastName(lastNameFilter);
        } else {
            return employeeRepository.findAll();
        }
    }

    public Set<Role> getRolesBySecretCode(String secretCode) {
        Employee employee = employeeRepository.findBySecretCodeForRole(secretCode);

        if (employee != null) {
            return employee.getEmployeeRoles();
        } else {
            return Collections.emptySet();
        }
    }

}
