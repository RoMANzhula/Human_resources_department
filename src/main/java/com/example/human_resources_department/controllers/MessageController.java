package com.example.human_resources_department.controllers;

import com.example.human_resources_department.models.Employee;
import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.MessageRepository;
import com.example.human_resources_department.repositories.UserRepository;
import com.example.human_resources_department.services.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MessageController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageService messageService;

    public MessageController(UserRepository userRepository, MessageRepository messageRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.messageService = messageService;
    }

    @GetMapping("/new-message")
    public String createNewMessage(
    ){
        return "newMessage";
    }

    @PostMapping("/new-message")
    public String addNewMessage(
            @AuthenticationPrincipal User coworker,
            @RequestParam String topic,
            @RequestParam String text,
            @RequestParam("file") MultipartFile fileForMessage,
            Model model
    ){

        if (!messageService.saveNewMessage(coworker, topic, text, fileForMessage)) {
            model.addAttribute("message", "Message is not created!");
        }

        return "redirect:/messages";
    }

    @GetMapping("/messages")
    public String allMessages(
            Model model
    ) {
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "allMessages";
    }

    @GetMapping("/message-filter")
    public String messageFilterByUserName(
            @RequestParam(required = false, defaultValue = "") String filterByAuthor,
            Model model
    ){
        Iterable<Message> listOfMessagesByFilter = messageService.getEmployees(filterByAuthor);

        model.addAttribute("messages", listOfMessagesByFilter);
        model.addAttribute("filterByAuthor", filterByAuthor);

        return "allMessages";
    }

}
