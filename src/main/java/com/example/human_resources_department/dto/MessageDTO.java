package com.example.human_resources_department.dto;


import com.example.human_resources_department.models.User;


import java.util.Date;

public class MessageDTO {
    private Long id;
    private String topic;
    private String text;
    private User author;
    private String fileName;
    private Date dateOfRegistration;

    public MessageDTO() {
    }

    public MessageDTO(User author, String topic, String text, String fileName) {
        this.author = author;
        this.topic = topic;
        this.text = text;
        this.fileName = fileName;
        this.dateOfRegistration = new Date();
    }

    public MessageDTO(Long id, String topic, String text, User author, String fileName, Date dateOfRegistration) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.author = author;
        this.fileName = fileName;
        this.dateOfRegistration = dateOfRegistration;
    }

    public MessageDTO(Long id, String topic, String text) {
        this.id = id;
        this.topic = topic;
        this.text = text;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

}