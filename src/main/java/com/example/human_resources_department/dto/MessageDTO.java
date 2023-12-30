package com.example.human_resources_department.dto;


import com.example.human_resources_department.models.User;

import java.util.Date;

public class MessageDTO {
    private Long id;
    private String topic;
    private String text;
    private String authorName;
    private String fileName;
    private Date dateOfRegistration;

    // Default constructor
    public MessageDTO() {
    }

    // Constructor for creating a new message
    public MessageDTO(String topic, String text, User author, String fileName) {
        this.topic = topic;
        this.text = text;
        this.authorName = author != null ? author.getUsername() : "<unknown>";
        this.fileName = fileName;
        // Assuming you want to set the current date for dateOfRegistration
        this.dateOfRegistration = new Date();
    }

    // Constructor for displaying all messages
    public MessageDTO(Long id, String topic, String text, User author, String fileName, Date dateOfRegistration) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.authorName = author != null ? author.getUsername() : "<unknown>";
        this.fileName = fileName;
        this.dateOfRegistration = dateOfRegistration;
    }

    // Constructor for displaying messages after filtering by author
    public MessageDTO(Long id, String topic, String text, String authorName, String fileName, Date dateOfRegistration) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.authorName = authorName;
        this.fileName = fileName;
        this.dateOfRegistration = dateOfRegistration;
    }

    // Constructor for editing a message
    public MessageDTO(Long id, String topic, String text, String fileName) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.fileName = fileName;
    }

    // Constructor for displaying user-specific messages
    public MessageDTO(Long id, String topic, String text, String fileName, Date dateOfRegistration) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.fileName = fileName;
        this.dateOfRegistration = dateOfRegistration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
    // ...
}