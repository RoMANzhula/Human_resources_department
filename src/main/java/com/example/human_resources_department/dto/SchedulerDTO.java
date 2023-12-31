package com.example.human_resources_department.dto;

import java.time.LocalDateTime;

public class SchedulerDTO {
    private Long id;
    private String taskTopic;
    private String taskText;
    private LocalDateTime deadline;
    private boolean completed;
    private LocalDateTime dateOfRegistration;
    private Long authorId; // Assuming User has an id field
    private LocalDateTime dateOfCompletion;
    private boolean reminderSent;

    public SchedulerDTO() {
    }

    public SchedulerDTO(
            Long id, String taskTopic, String taskText,
            LocalDateTime deadline, boolean completed,
            LocalDateTime dateOfRegistration, Long authorId,
            LocalDateTime dateOfCompletion, boolean reminderSent
    ) {
        this.id = id;
        this.taskTopic = taskTopic;
        this.taskText = taskText;
        this.deadline = deadline;
        this.completed = completed;
        this.dateOfRegistration = dateOfRegistration;
        this.authorId = authorId;
        this.dateOfCompletion = dateOfCompletion;
        this.reminderSent = reminderSent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTopic() {
        return taskTopic;
    }

    public void setTaskTopic(String taskTopic) {
        this.taskTopic = taskTopic;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }
}
