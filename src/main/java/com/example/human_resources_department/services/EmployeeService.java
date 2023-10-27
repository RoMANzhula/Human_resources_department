package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean addEmployee(Employee employee) {
        Employee employeeFromDB = employeeRepository.findByPhone(employee.getPhone());

        if (employeeFromDB != null) { //if employee didn't add
            return false;
        }

        employee.setActive(true); //come as active user
        employee.setDateOfRegistration(new Date()); //install date of registration
        employeeRepository.save(employee);

        return true;
    }
}
