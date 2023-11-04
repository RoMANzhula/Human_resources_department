package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.MessageRepository;
import com.example.human_resources_department.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class MessageService {
    private final UserRepository userRepository;
    private final LocalFileStorageService localFileStorageService;
    private final MessageRepository messageRepository;

    public MessageService(
            UserRepository userRepository,
            LocalFileStorageService localFileStorageService,
            MessageRepository messageRepository
    ) {
        this.userRepository = userRepository;
        this.localFileStorageService = localFileStorageService;
        this.messageRepository = messageRepository;
    }

    public boolean saveNewMessage(
            User coworker, String topic,
            String text, MultipartFile fileForMessage
    ) {
        Message message = new Message(
                topic,
                text,
                coworker
        );

        message.setDateOfRegistration(new Date());

        if (fileForMessage != null && !fileForMessage.getOriginalFilename().isEmpty()) {
            String uniqueFileName = localFileStorageService.storeFile(fileForMessage);
            message.setFileName(uniqueFileName);
        }

        messageRepository.save(message);

        return true;
    }

    public Iterable<Message> getEmployees(String filterByAuthor) {
        if (filterByAuthor != null && !filterByAuthor.isEmpty()) {
            User user = userRepository.findByUsername(filterByAuthor);
            return messageRepository.findByAuthor(user);
        } else {
            return messageRepository.findAll();
        }
    }

    public Message findById(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public void updateMessage(Message message, String topic, String text, MultipartFile file) {

        message.setDateOfRegistration(new Date());

        message.setTopic(topic);
        message.setText(text);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uniqueFileName = localFileStorageService.storeFile(file);
            message.setFileName(uniqueFileName);
        }

        messageRepository.save(message);
    }
}
