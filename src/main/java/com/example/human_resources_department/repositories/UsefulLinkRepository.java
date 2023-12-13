package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.UsefulLink;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsefulLinkRepository extends JpaRepository<UsefulLink, Long> {
    List<UsefulLink> findByOwnerId(Long ownerId);

    List<UsefulLink> findByOwner(User currentUser);
}

