package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.User;
import com.example.human_resources_department.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Controller
public class RegistrationController {
    @Value("${reCaptchaSecretSecretKey}")
    private String reCaptchaSecretSecretKey;

    private final UserService userService;

    public RegistrationController(
            UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(
            User user,
            @RequestParam String secretCode,
            @RequestParam("g-recaptcha-response") String recaptchaResponse,
            Model model
    ) {
        if (secretCode == null || secretCode.isEmpty()) {
            model.addAttribute("message", "Please, input secret code from HR-manager!");
            return "registration";
        }

        //secret is not in usr and it's in employee
        if (!userService.findSecretCodeInDataBase(secretCode) || !userService.isSecretCodeValid(secretCode)) {
            model.addAttribute("message", "Your code is wrong! Please try again or contact the HR-manager!");
            return "registration";
        }

        boolean isRecaptchaValid = verifyRecaptcha(recaptchaResponse);

        if (!isRecaptchaValid) {
            model.addAttribute("message", "reCAPTCHA verification failed. Please complete the reCAPTCHA.");
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

    private boolean verifyRecaptcha(String recaptchaResponse) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String params = "secret=" + reCaptchaSecretSecretKey + "&response=" + recaptchaResponse;
            String json = HttpUtils.sendPost(url, params);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> response = objectMapper.readValue(json, new TypeReference<>() {
            });

            return (boolean) response.get("success");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    public class HttpUtils {
        public static String sendPost(String url, String params) throws IOException {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = params.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;

                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                return response.toString();
            } finally {
                connection.disconnect();
            }
        }
    }

}
