package com.example.human_resources_department.repositories;

import com.example.human_resources_department.models.Meeting;
import com.example.human_resources_department.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByStaffContaining(User user);

    @Query("SELECT m.staff FROM Meeting m WHERE m.id = :meetingId")
    List<User> findAllStaffByMeetingId(@Param("meetingId") Long meetingId);
}
