package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Iterable<Message> findByAuthor(User filterByAuthor);
}
