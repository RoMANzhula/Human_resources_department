package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LocalFileStorageService localFileStorageService;


    public EmployeeService(
            EmployeeRepository employeeRepository,
            LocalFileStorageService localFileStorageService
    ) {
        this.employeeRepository = employeeRepository;
        this.localFileStorageService = localFileStorageService;
    }

    @Transactional(readOnly = true)
    public Iterable<Employee> getEmployees(String lastNameFilter) {
        if (lastNameFilter != null && !lastNameFilter.isEmpty()) {
            return employeeRepository.findByLastName(lastNameFilter);
        } else {
            return employeeRepository.findAll();
        }
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
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

    @Transactional
    public Set<Role> getRolesBySecretCode(String secretCode) {
        Employee employee = employeeRepository.findBySecretCodeForRole(secretCode);

        if (employee != null) {
            return employee.getEmployeeRoles();
        } else {
            return Collections.emptySet();
        }
    }

    @Transactional(readOnly = true)
    public Iterable<Employee> getFilteredEmployees(String lastNameFilter) {
        if (lastNameFilter != null && !lastNameFilter.isEmpty()) {
            return employeeRepository.findByLastName(lastNameFilter);
        } else {
            return employeeRepository.findAll();
        }
    }

    @Transactional
    public void updateEmployee(
            Employee employee,
            Boolean isActive,
            String firstName,
            String secondName,
            String lastName,
            Map<String, String> form,
            MultipartFile filePhoto
    ) {
        if (isActive != null) {
            employee.setActive(isActive);
        }
        if (firstName != null) {
            employee.setFirstName(firstName);
        }
        if (secondName != null) {
            employee.setSecondName(secondName);
        }
        if (lastName != null) {
            employee.setLastName(lastName);
        }

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        employee.getEmployeeRoles().clear();

        for (String check : form.keySet()) {
            if (roles.contains(check)) {
                employee.getEmployeeRoles().add(Role.valueOf(check));
            }
        }

        if (filePhoto != null && !filePhoto.getOriginalFilename().isEmpty()) {
            String uniqueFileName = localFileStorageService.storeFile(filePhoto);
            employee.setFilePhoto(uniqueFileName);
        }

        employeeRepository.save(employee);
    }

    @Transactional
    public Employee findEmployBySecretCodeWithRegistration(String secretCodeWithRegistration) {
        return employeeRepository.findBySecretCodeForRole(secretCodeWithRegistration);
    }
}
