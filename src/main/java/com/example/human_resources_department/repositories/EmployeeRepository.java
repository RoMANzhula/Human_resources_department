package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);

    Employee findByPhone(String phone);

    Employee findBySecretCodeForRole(String secretCodeForRole);

    Employee findEmployeeBySecretCodeForRole(String secretCodeWithRegistration);
}
