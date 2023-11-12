package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Scheduler;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.SchedulerRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
@EnableScheduling
public class SchedulerService {
    private final MailSenderService mailSenderService;
    private final SchedulerRepository schedulerRepository;

    public SchedulerService(
            MailSenderService mailSenderService,
            SchedulerRepository schedulerRepository
    ) {
        this.mailSenderService = mailSenderService;
        this.schedulerRepository = schedulerRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Scheduler> getAllTasksByUser(User currentUser) {
        return schedulerRepository.findAllByAuthor(currentUser);
    }

    @Transactional
    public void saveTask(Scheduler task, User currentUser) {
        if (task.getDeadline().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }

        task.setAuthor(currentUser);
        task.setDateOfRegistration(new Date());
        task.setCompleted(false);

        schedulerRepository.save(task);
    }

    @Transactional
    public void updateTaskStatus(Long taskId, boolean isCompleted) {
        Optional<Scheduler> optionalTask = schedulerRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Scheduler task = optionalTask.get();
            task.setCompleted(isCompleted);

            if (isCompleted) {
                task.setDateOfCompletion(new Date());
            }

            schedulerRepository.save(task);
        } else {
            System.out.println("Task is not found!");
        }
    }

    @Scheduled(cron = "0 */1 * * * *") //every minute
    @Transactional
    public void sendReminderAboutTask() {
        Iterable<Scheduler> listOfTasksToSend = schedulerRepository.findAll();

        for (Scheduler task : listOfTasksToSend) {
            User author = task.getAuthor();

            try {
                String addressTo = author.getEmail();
                String topic = "Reminder about your task!";
                String textMessage = "Dear " + author.getUsername() + ",\n\nPlease, don't forget about your task: " +
                        task.getTaskTopic() + "\n\nHave a good day!";

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime deadlineMinusOneHour = task.getDeadline().minus(1, ChronoUnit.HOURS);

                if (!task.isReminderSent() && now.isBefore(deadlineMinusOneHour)) {
                    mailSenderService.sendByMail(addressTo, topic, textMessage);

                    task.setReminderSent(true);
                    schedulerRepository.save(task);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.printf(e.getMessage());
            }
        }
    }
}
