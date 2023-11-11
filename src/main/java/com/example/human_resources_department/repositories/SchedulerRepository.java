package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Scheduler;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    Iterable<Scheduler> findAllByAuthor(User currentUser);
}
