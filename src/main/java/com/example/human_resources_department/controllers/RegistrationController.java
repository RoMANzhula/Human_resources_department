package com.example.human_resources_department.controllers;

import com.example.human_resources_department.dto.ReCaptchaResponseDto;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Value("${reCaptchaSecretKey}")
    private String reCaptchaSecretKey;

    private final static String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserService userService;

    private final RestTemplate restTemplate;

    public RegistrationController(
            UserService userService,
            RestTemplate restTemplate)
    {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(
            User user,
            @RequestParam String secretCode,
            @RequestParam("g-recaptcha-response") String reCaptchaResponse,
            Model model
    ) {
        String totalUrlReCaptcha = String.format(RECAPTCHA_URL, reCaptchaSecretKey, reCaptchaResponse);
        ReCaptchaResponseDto reCaptchaResponseDto = restTemplate.postForObject(
                totalUrlReCaptcha, Collections.emptyList(), ReCaptchaResponseDto.class);

        if (reCaptchaResponseDto == null || !reCaptchaResponseDto.isSuccess()) {
            model.addAttribute("reCaptchaError", "reCAPTCHA is empty! Fill it, please.");
        }

        if (secretCode == null || secretCode.isEmpty()) {
            model.addAttribute("message", "Please, input secret code from HR-manager!");
            return "registration";
        }

        //secret is not in usr and it's in employee
        if (!userService.findSecretCodeInDataBase(secretCode) || !userService.isSecretCodeValid(secretCode)) {
            model.addAttribute("message", "Your code is wrong! Please try again or contact the HR-manager!");
            return "registration";
        }

        if (!userService.addNewUser(user, secretCode)) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }

        return "duringRegistration";
    }


    @GetMapping("/duringRegistration")
    public String duringRegistrationPage() {
        return "duringRegistration";
    }

    @GetMapping("/activate/{activationCode}")
    public String activateRegistrationCode(
            @PathVariable String activationCode,
            Model model
    ) {
        boolean isActivatedUser = userService.activateUserByActivationCode(activationCode);

        if (isActivatedUser) {
            model.addAttribute("message", "User activation SUCCESSFUL!");
        } else {
            model.addAttribute("message", "Activation code NOT FOUND!");
        }

        return "login";
    }

}
