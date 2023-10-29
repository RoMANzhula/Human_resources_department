package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);

    Employee findByPhone(String phone);

    Employee findBySecretCodeForRole(String secretCodeForRole);

}
