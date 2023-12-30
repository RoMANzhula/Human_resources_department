package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Message;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByAuthor(User filterByAuthor);

    void deleteById(Long messageId);

    Iterable<Message> findByAuthorId(Long coworkerId);
}
