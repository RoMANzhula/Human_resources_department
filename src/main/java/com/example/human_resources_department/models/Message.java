package com.example.human_resources_department.models;

import jakarta.persistence.*;

import java.util.Date;

//this class will be Entity for messages between company employees
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String fileName;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date dateOfRegistration;

    public Message() {
    }

    public Message(String text, User author) {
        this.text = text;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<unknown>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
