package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
