package com.example.human_resources_department.services;

import com.example.human_resources_department.configurations.EncodersConfig;
import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Role;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.EmployeeRepository;
import com.example.human_resources_department.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Value("${hostname}")
    private String hostname;

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final UserRepository userRepository;
    private final EncodersConfig passwordEncoder;
    private final MailSenderService mailSenderService;

    public UserService(
            EmployeeRepository employeeRepository,
            EmployeeService employeeService,
            UserRepository userRepository,
            EncodersConfig encodersConfig,
            MailSenderService mailSenderService
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.userRepository = userRepository;
        this.passwordEncoder = encodersConfig;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with Name - " + username + " not found!");
        }
        return user;
    }

    public boolean addNewUser(User user, String secretCode) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) { //if user didn't add
            return false;
        }

        user.setActive(true); //come as active user
        user.setDateOfRegistration(new Date()); //install date of registration
        user.setActivationCode(UUID.randomUUID().toString()); //for mail activations
        user.setPassword(passwordEncoder.userPasswordEncoder().encode(user.getPassword())); //encryption
        user.setSecretCodeWithRegistration(secretCode);

        Set<Role> roles = employeeService.getRolesBySecretCode(secretCode);

        user.getUserRoles().clear();

        user.getUserRoles().addAll(roles);

        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Good day, %s! \n" +
                            "Welcome to our big family <Name Company>! \n" +
                            "Please, visit this link: http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );

            mailSenderService.sendByMail(user.getEmail(), "Activation code.", message);
        }

        return true;
    }


    public boolean activateUserByActivationCode(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

    public boolean findSecretCodeInDataBase(String secretCode) {
        User foundUser = userRepository.findBySecretCodeWithRegistration(secretCode);

        return foundUser == null;
    }

    public boolean isSecretCodeValid(String secretCode) {
        Employee employee = employeeRepository.findBySecretCodeForRole(secretCode);
        return employee != null;
    }

    public Iterable<User> getUsersByUsername(String usernameFilter) {
        if (usernameFilter != null && !usernameFilter.isEmpty()) {
            return userRepository.findAllByUsername(usernameFilter);
        } else {
            return userRepository.findAll();
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateCoworker(
            User coworker,
            Boolean isActive,
            String username,
            Map<String, String> form
    ) {

        if (isActive != null) {
            coworker.setActive(isActive);
        }
        if (username != null) {
            coworker.setUsername(username);
        }

        if (!form.isEmpty()) {
            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());

            coworker.getUserRoles().clear();

            for (String check : form.keySet()) {
                if (roles.contains(check)) {
                    coworker.getUserRoles().add(Role.valueOf(check));
                }
            }
        }

        userRepository.save(coworker);
    }

}
