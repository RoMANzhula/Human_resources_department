package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String activationCode);

    User findBySecretCodeWithRegistration(String secretCode);
}
