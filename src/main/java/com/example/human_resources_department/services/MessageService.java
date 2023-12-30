package com.example.human_resources_department.services;

import com.example.human_resources_department.dto.MessageDTO;
import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.MessageRepository;
import com.example.human_resources_department.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public boolean saveNewMessage(MessageDTO messageDTO) {
        Message message = new Message(
                messageDTO.getTopic(),
                messageDTO.getText(),
                messageDTO.getAuthor(),
                messageDTO.getFileName()
        );

        message.setDateOfRegistration(new Date());

        messageRepository.save(message);

        return true;
    }

    @Transactional(readOnly = true)
    public Iterable<MessageDTO> getEmployeeDTOs(String filterByAuthor) {
        if (filterByAuthor != null && !filterByAuthor.isEmpty()) {
            User user = userRepository.findByUsername(filterByAuthor);
            Iterable<Message> messages = messageRepository.findByAuthor(user);
            return convertToDTOs(messages);
        } else {
            Iterable<Message> messages = messageRepository.findAll();
            return convertToDTOs(messages);
        }
    }

    private Iterable<MessageDTO> convertToDTOs(Iterable<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message message : messages) {
            messageDTOs.add(new MessageDTO(
                    message.getId(),
                    message.getTopic(),
                    message.getText(),
                    message.getAuthor(),
                    message.getFileName(),
                    message.getDateOfRegistration()
            ));
        }
        return messageDTOs;
    }

    @Transactional(readOnly = true)
    public Message findById(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    @Transactional(readOnly = true)
    public MessageDTO findDTOById(Long messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);
        return convertToDTO(message);
    }

    private MessageDTO convertToDTO(Message message) {
        if (message != null) {
            return new MessageDTO(
                    message.getId(),
                    message.getTopic(),
                    message.getText(),
                    message.getAuthor(),
                    message.getFileName(),
                    message.getDateOfRegistration()
            );
        }
        return null;
    }

@Transactional
public void updateMessage(MessageDTO messageDTO, MultipartFile file) {
    Message message = messageRepository.findById(messageDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("Message not found"));

    message.setDateOfRegistration(new Date());
    message.setTopic(messageDTO.getTopic());
    message.setText(messageDTO.getText());

    if (file != null && !file.getOriginalFilename().isEmpty()) {
        String uniqueFileName = localFileStorageService.storeFile(file);
        message.setFileName(uniqueFileName);
    }

    messageRepository.save(message);
}

    @Transactional(readOnly = true)
    public List<MessageDTO> messagesListForCurrentUser(User currentUser) {
        List<Message> messages = messageRepository.findByAuthor(currentUser);
        return messages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Iterable<Message> messagesListForCurrentUserById(Long coworkerId) {
        return messageRepository.findByAuthorId(coworkerId);
    }

    @Transactional(readOnly = true)
    public Iterable<MessageDTO> getAllMessages() {
        Iterable<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOList = new ArrayList<>();

        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(message.getId());
            messageDTO.setTopic(message.getTopic());
            messageDTO.setText(message.getText());
            messageDTO.setAuthor(message.getAuthor());
            messageDTO.setDateOfRegistration(message.getDateOfRegistration());
            messageDTO.setFileName(message.getFileName());
            messageDTOList.add(messageDTO);
        }

        Collections.reverse(messageDTOList);

        return messageDTOList;
    }
}
