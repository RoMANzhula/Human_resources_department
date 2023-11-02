package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class MessageService {
    private final LocalFileStorageService localFileStorageService;
    private final MessageRepository messageRepository;

    public MessageService(LocalFileStorageService localFileStorageService, MessageRepository messageRepository) {
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
}
