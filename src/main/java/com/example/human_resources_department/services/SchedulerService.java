package com.example.human_resources_department.services;

import com.example.human_resources_department.models.Scheduler;
import com.example.human_resources_department.models.User;
import com.example.human_resources_department.repositories.SchedulerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository schedulerRepository) {
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
            schedulerRepository.save(task);
        } else {
            System.out.println("Task is not found!");
        }
    }
}
