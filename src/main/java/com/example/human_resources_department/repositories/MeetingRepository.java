package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByStaffContaining(User user);
}
