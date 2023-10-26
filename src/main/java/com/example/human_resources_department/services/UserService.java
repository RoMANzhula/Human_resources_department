package com.example.human_resources_department.services;

import com.example.human_resources_department.configurations.EncodersConfig;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EncodersConfig passwordEncoder;

    public UserService(UserRepository userRepository, EncodersConfig encodersConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = encodersConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with Name - " + username + " not found!");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userHR_ManagerFromDB = userRepository.findByUsername(user.getUsername());

        if (userHR_ManagerFromDB != null) { //if user didn't add
            return false;
        }

        user.setActive(true); //come as active user
        user.setRoles(Collections.singleton(Role.HR_MANAGER)); //only for hr-manager
        user.setDateOfRegistration(new Date()); //install date of registration
        user.setPassword(passwordEncoder.userPasswordEncoder().encode(user.getPassword())); //encryption
        userRepository.save(user);

        return true;
    }
}
